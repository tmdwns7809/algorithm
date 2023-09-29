import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
	
	static int ans = 0;
	
	static boolean[][] v = new boolean[5][5];
	
	static char[][] map;
	

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		map = new char[5][5];
		
		List<int[]> list = new ArrayList<int[]>();
		
		for (int i = 0; i < 5; i++) {
			map[i] = br.readLine().toCharArray();
			
			for (int j = 0; j < 5; j++) {
				if (map[i][j] == 'S')
					list.add(new int[] {i,j});
			}
		}
		
		for (int i = 0; i < list.size(); i++) {
			int[] p = list.get(i);
			v[p[0]][p[1]] = true;
			dfs(p, 0, 1, 1, new ArrayList<>());
			v[p[0]][p[1]] = false;
		}
		
		System.out.println(ans);
	}
	
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	
	static void dfs(int[] p, int d, int cnt, int s, List<int[]> list) {
		if (cnt == 7 || (cnt-s) > 3) {
			if (s >= 4) {
				ans++;
			}
				
			return;
		}
		
		if (d == 4) {
			for (int i = 0; i < list.size(); i++) {
				int[] p2 = list.get(i);
				
				dfs(p2, 0, cnt, s, new ArrayList());
			}
			
			return;
		}
		
		int nr = p[0] + dr[d];
		int nc = p[1] + dc[d];
		
		if (nr<0 || nc<0 || nr>=5 || nc>=5 || v[nr][nc]) {
			dfs(p, d+1, cnt, s, list);
			
			return;
		}
		
		v[nr][nc] = true;
		list.add(new int[] {nr, nc});
		dfs(p, d+1, cnt+1, map[nr][nc] == 'S' ? s+1 : s, list);
		list.remove(list.size()-1);
		dfs(p, d+1, cnt, s, list);
		v[nr][nc] = false;
	}

}

// 5x5 맵
// S와 Y로 이루어짐
// S를 4개이상 포함한 7개의 연결의 경우의 수