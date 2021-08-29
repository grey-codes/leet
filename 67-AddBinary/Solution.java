class Solution {
    public String addBinary(String a, String b) {
        StringBuilder sb = new StringBuilder();
        char[] aAr = a.toCharArray();
        char[] bAr = b.toCharArray();
        int aL = aAr.length, bL = bAr.length;
        int bothLen = Math.min(aL,bL);
        
        boolean checkA=aL!=bothLen;
        boolean checkB=bL!=bothLen;
        
        int carryBit=0;
        for (int i=0; i<bothLen; i++) {
            int res = (aAr[aL-i-1]-'0')+(bAr[bL-i-1]-'0')+carryBit;
            sb.append((res==0||res==2)?'0':'1');
            carryBit=(res==2||res==3)?1:0;
        }
        if (checkA) {
            for (int i=aL-bothLen-1; i>=0; i--) {
                int res = (aAr[i]-'0')+carryBit;
                sb.append((res==0||res==2)?'0':'1');
                carryBit=(res==2||res==3)?1:0;
            }
        }
        else if (checkB) {
            for (int i=bL-bothLen-1; i>=0; i--) {
                int res = (bAr[i]-'0')+carryBit;
                sb.append((res==0||res==2)?'0':'1');
                carryBit=(res==2||res==3)?1:0;
            }
        }
        if(carryBit==1)
            sb.append('1');
        return sb.reverse().toString();
    }
}
