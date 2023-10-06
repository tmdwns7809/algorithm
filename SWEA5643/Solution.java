import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {

	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream(Solution.class.getResource("./sample_input.txt").getFile()));
		
		// 입력 객체 생성
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// 테스트 케이스 횟수 읽기
		int T = Integer.parseInt(br.readLine());
		
		// 테스트 케이스만큼 반복
		for (int t = 1; t <= T; t++) {
			
			// 학생수 N 읽기
			int N = Integer.parseInt(br.readLine());
			
			// 학생수 M 읽기
			int M = Integer.parseInt(br.readLine());
			
			// 자기보다 큰 사람들을 담은 리스트배열
			List<Integer>[] arrTaller = new ArrayList[N+1];
			// 자기보다 작은 사람들을 담은 리스트배열
			List<Integer>[] arrShorter = new ArrayList[N+1];
			
			// 배열들 객체 생성
			for (int i = 1; i < N+1; i++) {
				arrTaller[i] = new ArrayList<>();
				arrShorter[i] = new ArrayList<>();
			}
			
			// 배열들 읽기
			for (int i = 0; i < M; i++) {
				// 문자열 토큰화 객체 생성
				StringTokenizer st = new StringTokenizer(br.readLine());
				
				// 키작은 사람
				int shortOne = Integer.parseInt(st.nextToken());
				// 키 큰 사람
				int tallOne = Integer.parseInt(st.nextToken());
				
				// 자기보다 큰 사람 저장
				arrTaller[shortOne].add(tallOne);
				
				// 자기보다 작은사람 저장
				arrShorter[tallOne].add(shortOne);
			}
			
			// 순위를 알 수 있는 사람 수
			int ans = 0;
			
			// 모든 사람 조사
			for (int i = 1; i < N+1; i++) {
				
				// 방문 배열
				boolean[] v = new boolean[N+1];
				
				// 자신 방문 처리
				v[i] = true;
				
				// 방문한 크기
				int size = 1;
				
				// 큐 객체 생성
				Queue<Integer> q = new LinkedList<>();
				
				// 큐에 자기 자신 넣기
				q.add(i);
				
				// 자기보다 큰 사람 검색
				while (!q.isEmpty()) {
					// 큐에서 꺼내기
					int p = q.poll();
					
					// 꺼낸 사람보다 큰 사람들 확인
					for (int j = 0; j < arrTaller[p].size(); j++) {
						// 꺼낸 사람보다 큰 사람
						int np = arrTaller[p].get(j);
						
						// 방문하지 않았다면
						if (!v[np]) {
							// 방문처리
							v[np] = true;
							// 방문크기 증가
							size++;
							// 큐에 삽입
							q.add(np);
						}
					}
				}
				
				// 큐에 자기 자신 넣기
				q.add(i);

				// 자기보다 작은 사람 검색
				while (!q.isEmpty()) {
					// 큐에서 꺼내기
					int p = q.poll();

					// 꺼낸 사람보다 작은 사람들 확인
					for (int j = 0; j < arrShorter[p].size(); j++) {
						// 꺼낸 사람보다 작은 사람
						int np = arrShorter[p].get(j);
						
						// 방문하지 않았다면
						if (!v[np]) {
							// 방문처리
							v[np] = true;
							// 방문크기 증가
							size++;
							// 큐에 삽입
							q.add(np);
						}
					}
				}
				
				// 자기 위아래 사람이 전부 정해져 있다면 순서 확정
				if (size == N)
					ans++;
			}
			
			// 답 출력
			System.out.println("#" + t + " " + ans);
			
		}
	}

}