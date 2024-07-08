import java.io.File;

public class Main {
    public static void main(String[] args) {
        String cwd=System.getProperty("user.dir");
        // String directoryPath = "C://"; 
        String directoryPath = cwd;
        

        File rootDirectory = new File(directoryPath);
        if (rootDirectory.exists() && rootDirectory.isDirectory()) {
            search(rootDirectory, 0);
        } else {
            System.out.println("目录不存在或不是一个目录: " + directoryPath);
        }
    }

    private static void search(File directory, int level) {
        File[] files = directory.listFiles();

        if (files != null) {
            for (File file : files) {
                printLevel(file.getName(), level);

                if (file.isDirectory()) {
                    search(file, level + 1);
                }
            }
        }
    }

    private static void printLevel(String name, int level) {
        for (int i = 0; i < level; i++) {
            System.out.print("    ");
        }
        System.out.println(name);
    }
}