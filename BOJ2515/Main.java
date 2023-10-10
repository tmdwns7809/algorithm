import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int S = Integer.parseInt(st.nextToken());
		
		Data[] pics = new Data[N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			pics[i] = new Data(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		
		Arrays.sort(pics);
		
		int[] dp = new int[pics[pics.length-1].height + 1];
		
		int pi = 0;
		for (int i = 1; i < dp.length; i++) {
			boolean isIn = false;
			while (pi < pics.length && i == pics[pi].height) {
				isIn = true;
				if ((pics[pi].height-S) >= 0)
					dp[i] = Math.max(dp[pics[pi].height-S] + pics[pi].price, 
							(pi==0||pics[pi].height!=pics[pi-1].height) ? 
									dp[i-1] : dp[i]);
				pi++;
			}
			if (!isIn)
				dp[i] = dp[i-1];
		}
		
		System.out.println(dp[dp.length - 1]);
	}

}

class Data implements Comparable<Data> {
	int height;
	int price;
	
	public Data(int height, int price) {
		this.height = height;
		this.price = price;
	}

	@Override
	public int compareTo(Data o) {
		return this.height-o.height;
	}
}