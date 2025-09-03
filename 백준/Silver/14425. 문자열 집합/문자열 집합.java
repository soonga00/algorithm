import java.io.*;
import java.util.*;

public class Main {
	static Node root;

    static class Node {
        Node[] children = new Node[26];
        boolean isEnd;
    }

    static void insert(String word) {
        Node node = root;
        for (char c : word.toCharArray()) {
            int idx = c - 'a';
            if (node.children[idx] == null) node.children[idx] = new Node();
            node = node.children[idx];
        }
        node.isEnd = true;
    }

    static boolean search(String word) {
        Node node = root;
        for (char c : word.toCharArray()) {
            int idx = c - 'a';
            if (node.children[idx] == null) return false;
            node = node.children[idx];
        }
        return node.isEnd;
    }
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		root = new Node();
		for (int i = 0; i < N; i++) {
			insert(br.readLine());
		}
		int cnt = 0;
		for (int i = 0; i < M; i++) {
			if (search(br.readLine()))cnt++;
		}
		System.out.println(cnt);
	}

}
