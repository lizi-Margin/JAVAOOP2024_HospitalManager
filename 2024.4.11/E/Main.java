import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt(); // 输入文章数量
        int m = scanner.nextInt(); // 输入不同单词数量

        // 创建一个 Map 用于统计每个单词在各篇文章中的出现次数
        Map<Integer, Integer>[] wordCounts = new HashMap[m + 1];
        for (int i = 1; i <= m; i++) {
            wordCounts[i] = new HashMap<>();
        }

        // 读取每篇文章
        for (int i = 0; i < n; i++) {
            int length = scanner.nextInt(); // 该篇文章中单词数量
            Set<Integer> appearedWords = new HashSet<>(); // 用于记录该篇文章中出现过的单词
            for (int j = 0; j < length; j++) {
                int word = scanner.nextInt(); // 单词编号
                if (!appearedWords.contains(word)) {
                    // 如果单词在当前文章中第一次出现，则增加该单词在该篇文章中的出现次数
                    appearedWords.add(word);
                    wordCounts[word].put(i + 1, wordCounts[word].getOrDefault(i + 1, 0) + 1);
                }
                // 增加该单词在全部文章中的出现次数
                wordCounts[word].put(0, wordCounts[word].getOrDefault(0, 0) + 1);
            }
        }

        // 输出结果
        for (int i = 1; i <= m; i++) {
            int size = wordCounts[i].size();
            if (size > 0) { size -=1;}
            System.out.println(size + " " + wordCounts[i].get(0));
        }

        scanner.close();
    }
}
