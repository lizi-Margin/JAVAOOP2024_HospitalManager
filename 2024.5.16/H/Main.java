import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String initialContent = scanner.nextLine();
        String mode = scanner.nextLine();
        String additionalContent = scanner.nextLine();
        StringBuilder fileContent = new StringBuilder(initialContent);

        scanner.close();

        switch (mode) {
            case "r":
                break;
            case "r+":
                 if (fileContent.length() == 0) {
                     break;
                 }
                if (additionalContent.length()>=fileContent.length())
                    fileContent = new StringBuilder(additionalContent); // true
                else{
                    for(int i = 0 ; i < additionalContent.length();i +=1){
                        fileContent.delete(i, i+1);
                        fileContent.insert(i, additionalContent.charAt(i));
                    }
                }
                break;
                
            case "w":
                fileContent = new StringBuilder(additionalContent);
                break;
            case "w+":
                fileContent = new StringBuilder(additionalContent);
                break;

            case "a":
                fileContent.append(additionalContent);
                break;
            case "a+":
                fileContent.append(additionalContent);
                break;

            case "x":
                if (fileContent.length() != 0) {
                    break;
                }
                fileContent.append(additionalContent);
                break;
            case "x+":
                if (fileContent.length() != 0) {
                    break;
                }
                fileContent.append(additionalContent);
                break;
            default:
                break;
        }

        System.out.println(fileContent.toString());
    }
}

