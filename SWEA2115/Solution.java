import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	
	static int N;
	static int M;
	static int C;
	static int ans;
	static int[][] map;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream(Solution.class.getResource("./sample_input.txt").getFile()));
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			
			map = new int[N][N];
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			ans = Integer.MIN_VALUE;
			
			dfs(0, 0, -1, -1);
			
			System.out.println("#" + t + " " + ans);
			
		}
		
	}
	
	static int h;
	
	static void dfs(int cnt, int idx, int s1, int s2) {
		if (cnt == 2) {
			
			h = Integer.MIN_VALUE;
			dfs2(s1, 0, 0, 0);
			int h1 = h;
			h = Integer.MIN_VALUE;
			dfs2(s2, 0, 0, 0);
			int h2 = h;
			
			int sum = h1+h2;
			
			if (sum > ans)
				ans = sum;
			
			return;
		}

		if (idx+M > N*N)
			return;
		
		int r = idx / N;
		int c = idx % N;
		
		if (s1 == -1) {
			if (c+M <= N)
				dfs(cnt+1, idx+M, idx, s2);
		} else {
			if (c+M <= N)
				dfs(cnt+1, idx+M, s1, idx);
		}
		
		dfs(cnt, idx+1, s1, s2);
	}
	
	static void dfs2(int s, int sum, int sum2, int idx) {
		if (sum > C) {
			
			int r = (s+idx-1) / N;
			int c = (s+idx-1) % N;
			
			sum2 -= map[r][c]*map[r][c];
			
			if (sum2 > h)
				h = sum2;
			
			return;
		}
		
		if (idx >= M) {
			if (sum2 > h)
				h = sum2;
			
			return;
		}
		
		int r = (s+idx) / N;
		int c = (s+idx) % N;
		
		dfs2(s, sum+map[r][c], sum2+map[r][c]*map[r][c], idx+1);
		dfs2(s, sum, sum2, idx+1);
	}

}
