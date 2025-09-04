import java.io.*;
import java.util.*;

public class Solution {
    static class Atom {
        int x, y, d, k;
        Atom(int x, int y, int d, int k) {
            this.x = x;
            this.y = y;
            this.d = d;
            this.k = k;
        }
    }

    static int T, N;
    static final int[] dx = {0, 0, -1, 1};
    static final int[] dy = {1, -1, 0, 0};

    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("res/d9_5648_input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            N = Integer.parseInt(br.readLine());
            List<Atom> atoms = new ArrayList<>();

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                int d = Integer.parseInt(st.nextToken());
                int k = Integer.parseInt(st.nextToken());

                atoms.add(new Atom((x + 1000) * 2, (y + 1000) * 2, d, k));
            }

            int energy = simulate(atoms);
            System.out.println("#" + t + " " + energy);
        }
    }

    static int simulate(List<Atom> atoms) {
        int totalEnergy = 0;

        while (!atoms.isEmpty()) {
            Map<Long, List<Atom>> map = new HashMap<>();

            for (Atom a : atoms) {
                a.x += dx[a.d];
                a.y += dy[a.d];

                if (a.x < 0 || a.x > 4000 || a.y < 0 || a.y > 4000) continue;

                long key = ((long)a.x << 13) | a.y;
                map.computeIfAbsent(key, k -> new ArrayList<>()).add(a);
            }

            List<Atom> next = new ArrayList<>();
            for (List<Atom> list : map.values()) {
                if (list.size() == 1) {
                    next.add(list.get(0));
                } else {
                    for (Atom a : list) {
                        totalEnergy += a.k;
                    }
                }
            }

            atoms = next;
        }

        return totalEnergy;
    }
}
