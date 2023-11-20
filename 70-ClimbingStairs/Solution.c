int climbStairs(int n) {
    if (n<=0) {
        return 0;
    }
    if (n==1) {
        return 1;
    }
    int res[3];
    res[1]=1;
    res[2]=2;
    for (short i=2; i<n; i++) {
        res[0]=res[1];
        res[1]=res[2];
        res[2]=res[1]+res[0];
    }
    return res[2];
}