import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			
			int N = Integer.parseInt(br.readLine());
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int[] arr = new int[N];
			for (int i = 0; i < N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			
			
			List<Integer> list = new ArrayList<Integer>();
			list.add(arr[0]);
			
			for (int i = 0; i < N; i++) {
				if (arr[i] >= list.get(list.size()-1))
					list.add(arr[i]);
				else {
					list.set(bs(list, arr[i]), arr[i]);
				}
			}
			
			StringBuilder sb = new StringBuilder();
			sb.append('#').append(t).append(' ').append(list.size());
			System.out.println(sb);
		}
	}

	static int bs(List<Integer> list, int n) {
		int lb = 0;
		int ub = list.size()-1;
		
		while (ub-lb > 1) {

			int mid = (lb+ub)/2;
			
			if (n >= list.get(mid)) {
				lb = mid;
			} else {
				ub = mid;
			}
		}
			
		return ub;
	}
}
