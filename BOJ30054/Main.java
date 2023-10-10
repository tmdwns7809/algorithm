import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		Data[] arr = new Data[N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			arr[i] = new Data(Integer.parseInt(st.nextToken()), 
					Integer.parseInt(st.nextToken()));
		}
		
		Arrays.sort(arr);
		
		int time = 1;
		
		Data[] qv = new Data[200000+1];
		
		Data in = null;
		Data out = null;
		
		int ans = Integer.MIN_VALUE;
		
		int i = 0;
		
		while (out != null || arr.length != i) {
			while (i < arr.length && arr[i].t2 == time) {
				if (in != null) {
					in.last = arr[i];
					arr[i].next = in;
				} else {
					out = arr[i];
				}
				in = arr[i];
				
				qv[arr[i].t1] = arr[i];
				
				i++;
			}
			
			if (time<200001 && qv[time] != null) {
				Data p = qv[time];
				if (p.last != null) {
					p.last.next = p.next;
				} else {
					in = p.next;
				}
				if (p.next != null) {
					p.next.last = p.last;
				} else {
					out = p.last;
				}
				qv[p.t1] = null;
				
				int wait = time - p.t2;
				if (wait > ans)
					ans = wait;
			} else if (out != null) {
				Data p = out;
				out = p.last;
				if (out != null)
					out.next = null;
				else 
					in = null;
				
				qv[p.t1] = null;
				
				int wait = time - p.t2;
				if (wait > ans)
					ans = wait;
			}

			time++;
		}
		
		System.out.println(ans);
	}

}

class Data implements Comparable<Data> {
	Data last = null;
	Data next = null;
	
	int t1;
	int t2;
	
	public Data(int t1, int t2) {
		this.t1 = t1;
		this.t2 = t2;
	}

	@Override
	public int compareTo(Data o) {
		if (this.t2 != o.t2)
			return this.t2-o.t2;
		else
			return this.t1-o.t1;
	}
}