import java.util.*;

class Solution {
    public int[] solution(String today, String[] terms, String[] privacies) {
        List<Integer> answer = new ArrayList<>();
        HashMap<String, Integer> map = new HashMap<>();
        
        for (String t : terms) {
            String[] row = t.split(" ");
            map.put(row[0], Integer.parseInt(row[1]));
        }
        
        String[] todays = today.split("\\.");
        int tY = Integer.parseInt(todays[0]);
        int tM = Integer.parseInt(todays[1]);
        int tD = Integer.parseInt(todays[2]);
        
        for (int i = 0; i < privacies.length; i++) {
            String[] p = privacies[i].split(" ");
            String type = p[1];
            String[] days = p[0].split("\\.");
            
            int pY = Integer.parseInt(days[0]);
            int pM = Integer.parseInt(days[1]);
            int pD = Integer.parseInt(days[2]);
            pM += map.get(type);
            pY += (pM - 1) / 12;
            pM = (pM - 1) % 12 + 1;
            pD -= 1;
            if (pY < tY
             || (pY == tY && pM < tM)
             || (pY == tY && pM == tM && pD < tD)) {
                answer.add(i + 1);
            }
        }
        
        return answer.stream().mapToInt(Integer::intValue).toArray();
    }
}
