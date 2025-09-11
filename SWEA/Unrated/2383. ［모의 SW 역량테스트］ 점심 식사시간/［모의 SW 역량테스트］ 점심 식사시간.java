import java.io.*;
import java.util.*;

public class Solution {
	static int T, N, answer, personCnt;
	static Stair[] stair;
	static ArrayDeque<Person>[] sq;
	static ArrayList<Person> person;
	static class Person {
		int x, y;
		Stair s;
		Person(int x, int y) {
			this.x = x;
			this.y = y;
		}
		
		int getArriveTime() {
			return Math.abs(x-s.x) + Math.abs(y-s.y);
		}
	}
	
	static class Stair {
		int x, y, l;
		Stair(int x, int y, int l) {
			this.x = x;
			this.y = y;
			this.l = l;
		}
	}

    static int simulateStair(List<Integer> arriveTimes, int stairLen) {
    	if (arriveTimes.isEmpty()) return 0;
        Collections.sort(arriveTimes);

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int a : arriveTimes) {
            int ready = a + 1;

            while (!pq.isEmpty() && pq.peek() <= ready) pq.poll();

            if (pq.size() < 3) {
                pq.add(ready + stairLen);
            } else {
                int earliest = pq.poll();
                ready = earliest;
                while (!pq.isEmpty() && pq.peek() <= ready) pq.poll();
                pq.add(ready + stairLen);
            }
        }

        int last = 0;
        while (!pq.isEmpty()) last = pq.poll();
        return last;
    }

    static int simulation() {
        List<Integer> arr0 = new ArrayList<>();
        List<Integer> arr1 = new ArrayList<>();

        for (Person p : person) {
            if (p.s == stair[0]) arr0.add(p.getArriveTime());
            else arr1.add(p.getArriveTime());
        }

        int t0 = simulateStair(arr0, stair[0].l);
        int t1 = simulateStair(arr1, stair[1].l);

        return Math.max(t0, t1);
    }

    static void dfs(int idx) {
        if (idx == personCnt) {
            int v = simulation();
            answer = Math.min(answer, v);
            return;
        }
        Person p = person.get(idx);


        p.s = stair[0];
        dfs(idx + 1);


        p.s = stair[1];
        dfs(idx + 1);
    }
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
		    N = Integer.parseInt(br.readLine());
		    stair = new Stair[2];
		    person = new ArrayList<>();
		    personCnt = 0;
		    answer = Integer.MAX_VALUE;

		    int stairIdx = 0;
		    for (int i = 0; i < N; i++) {
		        st = new StringTokenizer(br.readLine(), " ");
		        for (int j = 0; j < N; j++) {
		            int v = Integer.parseInt(st.nextToken());
		            if (v == 1) { person.add(new Person(i, j)); personCnt++; }
		            else if (v >= 2) stair[stairIdx++] = new Stair(i, j, v);
		        }
		    }
		    dfs(0);
		    sb.append('#').append(t).append(' ').append(answer).append('\n');
		}

		System.out.print(sb);

	}

}
