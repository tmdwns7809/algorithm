import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			int n = Integer.parseInt(br.readLine());
			
			int[][] p = new int[n+2][2];
			
			for (int i = 0; i < n+2; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				
				p[i][0] = Integer.parseInt(st.nextToken());
				p[i][1] = Integer.parseInt(st.nextToken());
				
				
			}
			
			boolean[] v = new boolean[n+2];
			int des = n+1;
			String ans = "happy";
			
			mainLoop : for (int i = 0; i < n+1; i++) {
				int close = Integer.MAX_VALUE;
				int closeIdx = 0;
				for (int j = 0; j < n+1; j++) {
					if (v[j])
						continue;
					
					int dis = Math.abs(p[j][0] - p[des][0]) + Math.abs(p[j][1] - p[des][1]);
					
					if (dis <= 1000) {
						if (j == 0) {
							break mainLoop;
						}
						
						if (dis < close) {
							close = dis;
							closeIdx = j;
						}
					}
				}
				if (close == Integer.MAX_VALUE) {
					ans = "sad";
					break;
				}
				v[closeIdx] = true;
				des = closeIdx;
			}
			
			System.out.println(ans);
		}
	}

}
