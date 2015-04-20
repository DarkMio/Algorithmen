package Vorlesung2;

public class binarySearch {

    public static void main(String[] args) {
        System.out.print(contains(new int[]{1, 3, 4, 5, 6, 7, 8, 11, 12, 14}, 8));
    }

    public static boolean contains(int[] a, int z) {
        if (a[0] > z || a[a.length-1] < z) return false;
        return containsRek(a, z, 0, a.length-1);
    }

    public static boolean containsRek(int[] a, int z, int l, int r){
        if (l == r) return a[l] == z;
        int g = (l+r) / 2;
        if (a[g] == z) return true;
        if (a[g] > z) return containsRek(a, z, l, g-1);
        return containsRek(a, z, g+1, r);
    }
}
