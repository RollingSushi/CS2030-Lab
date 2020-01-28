public class Combination {
	public static int comb(int n , int r)
	{
		if( r== 0 || n == r)
			return 1;
		else
			return comb(n-1,r)+comb(n-1,r-1);
	}
}