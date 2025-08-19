import java.io.*;
import java.util.*;

public class Solution{
    static int T, H, W, N;
    static char[][] g;
    static final int[] dx = { -1, 1, 0, 0 };
    static final int[] dy = { 0, 0, -1, 1 };

    static int dirFrom(char ch) {
        if (ch == '^') return 0;
        if (ch == 'v') return 1;
        if (ch == '<') return 2;
        return 3;
    }

    static char charFrom(int d) {
        return new char[]{'^','v','<','>'}[d];
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for (int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine());
            H = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            g = new char[H][W];

            int x = 0, y = 0, dir = 0;
            for (int i = 0; i < H; i++) {
                char[] row = br.readLine().toCharArray();
                for (int j = 0; j < W; j++) {
                    char c = row[j];
                    g[i][j] = c;
                    if (c=='^' || c=='v' || c=='<' || c=='>') {
                        x = i; y = j; dir = dirFrom(c);
                    }
                }
            }

            N = Integer.parseInt(br.readLine());
            char[] commands = br.readLine().toCharArray();

            for (char c : commands) {
                switch (c) {
                    case 'U': {
                        dir = 0;
                        int nx = x + dx[dir], ny = y + dy[dir];
                        g[x][y] = charFrom(dir);
                        if (0 <= nx && nx < H && 0 <= ny && ny < W && g[nx][ny] == '.') {
                            g[x][y] = '.';
                            x = nx; y = ny;
                            g[x][y] = charFrom(dir);
                        }
                        break;
                    }
                    case 'D': {
                        dir = 1;
                        int nx = x + dx[dir], ny = y + dy[dir];
                        g[x][y] = charFrom(dir);
                        if (0 <= nx && nx < H && 0 <= ny && ny < W && g[nx][ny] == '.') {
                            g[x][y] = '.';
                            x = nx; y = ny;
                            g[x][y] = charFrom(dir);
                        }
                        break;
                    }
                    case 'L': {
                        dir = 2;
                        int nx = x + dx[dir], ny = y + dy[dir];
                        g[x][y] = charFrom(dir);
                        if (0 <= nx && nx < H && 0 <= ny && ny < W && g[nx][ny] == '.') {
                            g[x][y] = '.';
                            x = nx; y = ny;
                            g[x][y] = charFrom(dir);
                        }
                        break;
                    }
                    case 'R': {
                        dir = 3;
                        int nx = x + dx[dir], ny = y + dy[dir];
                        g[x][y] = charFrom(dir);
                        if (0 <= nx && nx < H && 0 <= ny && ny < W && g[nx][ny] == '.') {
                            g[x][y] = '.';
                            x = nx; y = ny;
                            g[x][y] = charFrom(dir);
                        }
                        break;
                    }
                    case 'S': {
                        int nx = x + dx[dir], ny = y + dy[dir];
                        while (0 <= nx && nx < H && 0 <= ny && ny < W) {
                            if (g[nx][ny] == '*') {
                                g[nx][ny] = '.';
                                break;
                            } else if (g[nx][ny] == '#') {
                                break;
                            }
                            nx += dx[dir];
                            ny += dy[dir];
                        }
                        break;
                    }
                }
            }

            sb.append('#').append(t).append(' ');
            for (int i = 0; i < H; i++) {
                sb.append(g[i]).append('\n');
            }
        }
        System.out.print(sb);
    }
}
