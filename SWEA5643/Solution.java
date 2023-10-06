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
		
		// �Է� ��ü ����
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// �׽�Ʈ ���̽� Ƚ�� �б�
		int T = Integer.parseInt(br.readLine());
		
		// �׽�Ʈ ���̽���ŭ �ݺ�
		for (int t = 1; t <= T; t++) {
			
			// �л��� N �б�
			int N = Integer.parseInt(br.readLine());
			
			// �л��� M �б�
			int M = Integer.parseInt(br.readLine());
			
			// �ڱ⺸�� ū ������� ���� ����Ʈ�迭
			List<Integer>[] arrTaller = new ArrayList[N+1];
			// �ڱ⺸�� ���� ������� ���� ����Ʈ�迭
			List<Integer>[] arrShorter = new ArrayList[N+1];
			
			// �迭�� ��ü ����
			for (int i = 1; i < N+1; i++) {
				arrTaller[i] = new ArrayList<>();
				arrShorter[i] = new ArrayList<>();
			}
			
			// �迭�� �б�
			for (int i = 0; i < M; i++) {
				// ���ڿ� ��ūȭ ��ü ����
				StringTokenizer st = new StringTokenizer(br.readLine());
				
				// Ű���� ���
				int shortOne = Integer.parseInt(st.nextToken());
				// Ű ū ���
				int tallOne = Integer.parseInt(st.nextToken());
				
				// �ڱ⺸�� ū ��� ����
				arrTaller[shortOne].add(tallOne);
				
				// �ڱ⺸�� ������� ����
				arrShorter[tallOne].add(shortOne);
			}
			
			// ������ �� �� �ִ� ��� ��
			int ans = 0;
			
			// ��� ��� ����
			for (int i = 1; i < N+1; i++) {
				
				// �湮 �迭
				boolean[] v = new boolean[N+1];
				
				// �ڽ� �湮 ó��
				v[i] = true;
				
				// �湮�� ũ��
				int size = 1;
				
				// ť ��ü ����
				Queue<Integer> q = new LinkedList<>();
				
				// ť�� �ڱ� �ڽ� �ֱ�
				q.add(i);
				
				// �ڱ⺸�� ū ��� �˻�
				while (!q.isEmpty()) {
					// ť���� ������
					int p = q.poll();
					
					// ���� ������� ū ����� Ȯ��
					for (int j = 0; j < arrTaller[p].size(); j++) {
						// ���� ������� ū ���
						int np = arrTaller[p].get(j);
						
						// �湮���� �ʾҴٸ�
						if (!v[np]) {
							// �湮ó��
							v[np] = true;
							// �湮ũ�� ����
							size++;
							// ť�� ����
							q.add(np);
						}
					}
				}
				
				// ť�� �ڱ� �ڽ� �ֱ�
				q.add(i);

				// �ڱ⺸�� ���� ��� �˻�
				while (!q.isEmpty()) {
					// ť���� ������
					int p = q.poll();

					// ���� ������� ���� ����� Ȯ��
					for (int j = 0; j < arrShorter[p].size(); j++) {
						// ���� ������� ���� ���
						int np = arrShorter[p].get(j);
						
						// �湮���� �ʾҴٸ�
						if (!v[np]) {
							// �湮ó��
							v[np] = true;
							// �湮ũ�� ����
							size++;
							// ť�� ����
							q.add(np);
						}
					}
				}
				
				// �ڱ� ���Ʒ� ����� ���� ������ �ִٸ� ���� Ȯ��
				if (size == N)
					ans++;
			}
			
			// �� ���
			System.out.println("#" + t + " " + ans);
			
		}
	}

}