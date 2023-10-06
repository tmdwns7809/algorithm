import java.io.*;
import java.util.*;

public class Solution {
    static int n,w,h;
    static int[][] arr;
    static int ans;
    static int[] nums;
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};
    static int[][] copy;
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st;
        int t = Integer.parseInt(bf.readLine());
        for(int tc=1; tc<=t; tc++) {
            st = new StringTokenizer(bf.readLine());
            n = Integer.parseInt(st.nextToken()); // 횟수
            w = Integer.parseInt(st.nextToken()); 
            h = Integer.parseInt(st.nextToken());
            
            arr = new int[h][w];
            nums = new int[n];
            ans = h*w;
            
            for(int i=0; i<h; i++) {
                st = new StringTokenizer(bf.readLine());
                for(int j=0; j<w; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            select(0);
//            copy = new int[h][w];
//            for(int i=0; i<h; i++) {
//                copy[i] = arr[i].clone();
//            }
//            
//            nums = new int[] {2, 2, 6};
//            for(int k=0; k<n; k++) { // 뽑은 구슬만큼 반복
//                int tx = nums[k]; // 떨어뜨릴 x좌표
//                System.out.println(tx);
//                for(int i=0; i<h; i++) {
//                    if(copy[i][tx] == 0) continue;
//                    dfs(i, tx);
//                    break;
//                }
//                for(int i=0; i<h; i++) {
//                    System.out.println(Arrays.toString(copy[i]));
//                }
//                System.out.println();
//            }
            // 남은 벽돌의 개수
            System.out.println("#"+tc+" "+ans);
        }
    }
    // 중복 순열
    private static void select(int cnt) {
        if(cnt == n) {
            // 배열 복사
            int[][] copy = new int[h][w];
            for(int i=0; i<h; i++) {
                copy[i] = arr[i].clone();
            }
            
//            nums = new int[] {2, 2, 6};
            for(int k=0; k<n; k++) { // 뽑은 구슬만큼 반복
                int tx = nums[k]; // 떨어뜨릴 x좌표
                for(int i=0; i<h; i++) {
                    if(copy[i][tx] == 0) continue;
                    if(copy[i][tx] == 1) {
                        copy[i][tx] = 0; 
                        continue;
                    }
                    dfs(i, tx);
                    break;
                }
            }
            
            checkBlock();
            return;
        }
        
        for(int i=0; i<w; i++) {
            nums[cnt] = i;
            select(cnt+1);
        }
    }
    private static void dfs(int startY, int startX) {
        boolean[][] visited = new boolean[h][w];
        Stack<block> s = new Stack<>();
        s.add(new block(startY, startX, copy[startY][startX]));
        visited[startY][startX] = true;
        
        while(!s.isEmpty()) {
            block b = s.pop();
            int y = b.y;
            int x = b.x;
            int range = b.range;
            
            for(int i=0; i<4; i++) {
                for(int j=1; j<range; j++) {
                    int ny = y+dy[i]+j;
                    int nx = x+dx[i]+j;
                    
                    if(!outOfIdx(ny, nx)) break;
                    if(visited[ny][nx]) break;
                    if(copy[ny][nx] == 0) break;
                    
                    visited[ny][nx] = true;
                    copy[ny][nx] = 0;
                    s.add(new block(ny, nx, copy[ny][nx]));
                }
            }
        }
        removeBlock();
    }
    
    private static void removeBlock() {
        for(int i=0; i<w; i++) {
            int idx = h-1;
            for(int j=h-1; j>=0; j--) {
                if(copy[j][i] != 0) { // 안지워졌으면
                    copy[idx][i] = copy[j][i];
                    idx--;
                }
            }
            
            for(int j=idx; j>=0; j--) { // 나머지부분 0으로
                copy[j][i] = 0;
            }
        }
    }
    
    private static void checkBlock() {
        int temp = 0;
        for(int i=0; i<w; i++) {
            for(int j=h-1; j>=0; j--) {
                if(copy[j][i] == 0) break;
                temp++;
            }
        }
        
        ans = Math.min(ans, temp);
    }
    
    private static boolean outOfIdx(int ny, int nx) {
        if(ny>=0 && ny<h && nx>=0 && nx<w) {
            return true;
        }
        return false;
    }
    
    static class block {
        int y, x, range;

        public block(int y, int x, int range) {
            super();
            this.y = y;
            this.x = x;
            this.range = range;
        }

        @Override
        public String toString() {
            return "block [y=" + y + ", x=" + x + ", range=" + range + "]";
        }
    }
}