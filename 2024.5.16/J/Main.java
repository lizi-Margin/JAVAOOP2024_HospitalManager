import java.util.*;
public class Main {
    public static void main(String[] args) {
        test();;
   }
    static void test( ){

        ArrayList<Integer> list = get();


        int more = list.size()/2 + 1;
        
        int sum = 0;
        for (int i = 0; i < more ; i += 1){
            sum +=list.get(i)/2 + 1;
        }
        print(sum); 
        
 
    }
    static void print(int i){

        System.out.println(i);
    }
    static ArrayList<Integer> get (){
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.nextLine();
        ArrayList<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < n; i++) {
            list.add(scanner.nextInt());
        }
        Collections.sort(list);
        scanner.close();
        return list;


    }
}

