public class Main {

    public static int count = 0;
    public static void main(String[] args) {
        int[] fdsa = new int[3];
        fdsa = asdf(fdsa);
        System.out.println(fdsa[2]);

    }

    public static int[] asdf(int[] asdfasdf){
        asdfasdf[2]++;
        count ++;
        if(count != 4) asdf(asdfasdf);

        return asdfasdf;
    }
}
