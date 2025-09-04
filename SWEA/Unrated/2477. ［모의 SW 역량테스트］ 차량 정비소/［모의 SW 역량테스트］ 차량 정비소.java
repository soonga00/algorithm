import java.io.*;
import java.util.*;

public class Solution {
    static int T, N, M, K, A, B;

    static class Customer {
        int id, tk;
        int receipt, repair;
        int start, end;
        Customer(int id, int tk) {
            this.id = id;
            this.tk = tk;
        }
    }

    public static void main(String[] args) throws Exception {
    	//System.setIn(new FileInputStream("res/d9_2477_input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine(), " ");
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            A = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());

            int[] receiptTime = new int[N + 1];
            int[] repairTime = new int[M + 1];
            st = new StringTokenizer(br.readLine(), " ");
            for (int i = 1; i <= N; i++) receiptTime[i] = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine(), " ");
            for (int i = 1; i <= M; i++) repairTime[i] = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine(), " ");
            Customer[] customers = new Customer[K + 1];
            for (int i = 1; i <= K; i++) {
                int tk = Integer.parseInt(st.nextToken());
                customers[i] = new Customer(i, tk);
            }

            PriorityQueue<Customer> waitingReceipt = new PriorityQueue<>((c1, c2) -> Integer.compare(c1.id, c2.id));
            PriorityQueue<Customer> waitingRepair = new PriorityQueue<>((c1, c2) -> {
                if (c1.end != c2.end) return Integer.compare(c1.end, c2.end);
                if (c1.receipt != c2.receipt) return Integer.compare(c1.receipt, c2.receipt);
                return Integer.compare(c1.id, c2.id);
            });

            Customer[] usingReceipt = new Customer[N + 1];
            int[] receiptEndTime = new int[N + 1];
            Customer[] usingRepair = new Customer[M + 1];
            int[] repairEndTime = new int[M + 1];

            int time = 0, done = 0;

            while (done < K) {
                for (int i = 1; i <= K; i++) {
                    if (customers[i].tk == time) {
                        waitingReceipt.add(customers[i]);
                    }
                }

                for (int i = 1; i <= N; i++) {
                    if (usingReceipt[i] != null && receiptEndTime[i] == time) {
                        Customer c = usingReceipt[i];
                        usingReceipt[i] = null;
                        waitingRepair.add(c);
                    }
                }

                for (int i = 1; i <= M; i++) {
                    if (usingRepair[i] != null && repairEndTime[i] == time) {
                        usingRepair[i] = null;
                        done++;
                    }
                }

                for (int i = 1; i <= N; i++) {
                    if (usingReceipt[i] == null && !waitingReceipt.isEmpty()) {
                        Customer c = waitingReceipt.poll();
                        usingReceipt[i] = c;
                        c.receipt = i;
                        c.start = time;
                        c.end = time + receiptTime[i];
                        receiptEndTime[i] = c.end;
                    }
                }

                for (int i = 1; i <= M; i++) {
                    if (usingRepair[i] == null && !waitingRepair.isEmpty()) {
                        Customer c = waitingRepair.poll();
                        usingRepair[i] = c;
                        c.repair = i;
                        c.start = time;
                        c.end = time + repairTime[i];
                        repairEndTime[i] = c.end;
                    }
                }

                time++;
            }

            int answer = 0;
            for (int i = 1; i <= K; i++) {
                if (customers[i].receipt == A && customers[i].repair == B) {
                    answer += customers[i].id;
                }
            }
            if (answer == 0) answer = -1;

            sb.append("#").append(t).append(" ").append(answer).append("\n");
        }
        System.out.print(sb);
    }
}
