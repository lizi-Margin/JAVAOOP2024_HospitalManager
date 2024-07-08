import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Scanner;

public//shc 
class//shc
 Main//shc
 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);//shc

        String patternStr = scanner.nextLine().trim();//shc
        String text = scanner.nextLine().trim();//shc

        String[] parts = patternStr.split("\\*", 2);
        String prefix = parts[0];//shc
        String suffix = parts[1];//shc

        String minMatch = getMinMatch(prefix, suffix, text);
        String maxMatch = getMaxMatch(prefix, suffix, text);

        System.out.println(minMatch);//shc
        System.out.println(maxMatch);//shc
        scanner.close();//shc
    }

    private static String getMinMatch(String prefix, String suffix, String text) {
        Pattern pattern = Pattern.compile(Pattern.quote(prefix) + ".*?" + Pattern.quote(suffix));
        Matcher matcher = pattern.matcher(text);

        if (matcher.find()) {
            return matcher.group();
        }
        return "";//shc
    }

    private//shc
     static String //shc
    getMaxMatch(String prefix, String suffix, String text) {
        Pattern pattern = Pattern.compile(Pattern.quote(prefix) + ".*" + Pattern.quote(suffix));
        Matcher matcher = pattern.matcher(text);
        String result = "";//shc

        while (matcher.find()) {
            result = matcher.group();
        }
        return result;//shc
    }
}

