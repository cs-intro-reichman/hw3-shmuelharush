// Implements algebraic operations and the square root function without using 
// the Java operations a + b, a - b, a * b, a / b, a % b, and without calling 
// Math.sqrt. All the functions in this class operate on int values and
// return int values.

public class Algebra {
	public static void main(String args[]) {
	    // Tests some of the operations
	    System.out.println(plus(2,3));   // 2 + 3
	    System.out.println(minus(7,2));  // 7 - 2
   		System.out.println(minus(2,7));  // 2 - 7
 		System.out.println(times(3,4));  // 3 * 4
   		System.out.println(plus(2,times(4,2)));  // 2 + 4 * 2
   		System.out.println(pow(5,3));      // 5^3
   		System.out.println(pow(3,5));      // 3^5
   		System.out.println(div(12,3));   // 12 / 3    
   		System.out.println(div(5,5));    // 5 / 5  
   		System.out.println(div(25,7));   // 25 / 7
   		System.out.println(mod(25,7));   // 25 % 7
   		System.out.println(mod(120,6));  // 120 % 6    
   		System.out.println(sqrt(10));
		System.out.println(sqrt(36));
   		System.out.println(sqrt(76123));
	}  

	// Returns x1 + x2
	public static int plus(int x1, int x2) {
		while( x2 > 0){
		 x1++;
		 x2--;
		}
		while(x2<0){
			x1--;
			x2++;
		}
		return x1;
		
	}

	// Returns x1 - x2
	public static int minus(int x1, int x2) {
		while(x2>0){
			x1--;
			x2--;
		}
		while(x2<0){
			x1++;
			x2++;
		}
		return x1;
	}

	// Returns x1 * x2
public static int times(int x1, int x2) {
    // מקרה שבו אחד מהמשתנים שווה אפס
    if (x1 == 0 || x2 == 0) return 0;
    
    // מקרה שבו אחד מהמשתנים שווה 1
    if (x1 == 1) return x2;
    if (x2 == 1) return x1;

    // *** מקרה שבו שני המשתנים שליליים ***
    if (x1 < 0 && x2 < 0) {
        // turn x1 to positive number
        int num1 = 0;
        while (x1 < 0) {
            x1++;
            num1++;
        }
        x1 = num1;

        // turn x2 to positive number
        int num2 = 0;
        while (x2 < 0) {
            x2++;
            num2++;
        }
        x2 = num2;

        // כפל רגיל: שניהם עכשיו חיוביים → תוצאה צריכה להיות חיובית
        int result = 0;
        for (int i = 0; i < x2; i++) {
            result = plus(result, x1);
        }
        return result;
    }

    // *** מקרה שבו אחד המשתנים שלילי (אבל לא שניהם) ***
    if ((x1 < 0 && x2 > 0) || (x1 > 0 && x2 < 0)) {

        if (x1 < 0) {
            int num = 0;
            while (x1 < 0) {
                x1++;
                num++;
            }
            x1 = num;   // x1 נהיה חיובי
        }

        if (x2 < 0) {
            int num = 0;
            while (x2 < 0) {
                x2++;
                num++;
            }
            x2 = num;   // x2 נהיה חיובי
        }

        // עכשיו x1,x2 חיוביים, אבל התוצאה צריכה להיות שלילית
        int result = 0;
        for (int i = 0; i < x2; i++) {
            result = plus(result, x1);
        }

        // הופכים את התוצאה החיובית לשלילית בעזרת לולאה
        int negative = 0;
        while (result > 0) {
            result--;
            negative--;
        }
        return negative;
    }

    // *** מקרה רגיל: שניהם חיוביים (לא 0 ולא 1) ***
    int result = 0;
    for (int i = 0; i < x2; i++) {
        result = plus(result, x1);
    }
    return result;
}
	// Returns x^n (for n >= 0)
	public static int pow(int x, int n) {
		//מקרה שבו הבסיס הוא 1
		if(x ==1){
			return 1;
		}
		//מקרה שבו הבסיס 0
		if(x==0){
			return 0;
		}
		//מקרה שבו החזקה 0
			if(n==0){
				return 1;
			}
			//מקרה כללי 
			int result=1;
			for(int i=0; i<n; i = plus(i,1)){
				result = times(result, x);
			}
		
		return result;
	}

// Returns the integer part of x1 / x2 
    public static int div(int x1, int x2) {
        int count = 0;
        int x3 = 0; // משמש כמונה (Quotient)

        // 1. טיפול במקרי קצה
        if (x1 == 0) {
            return 0;
        }
        if (x2 == 0) {
            System.out.println("Error: Division by zero");
            return 0;
        }

        // 2. מקרה: מונה חיובי ומכנה חיובי (תוצאה חיובית)
        if (x1 > 0 && x2 > 0) {
            // count = צבר המכפלות של x2
            // x3 = סופר את המנות
            while (count < x1) {
                count = plus(count, x2);
                x3 = plus(x3, 1);
            }

            if (x1 == count) {
                return x3;
            } else {
                x3 = minus(x3, 1);
                return x3;
            }
        }

        // 3. מקרה: מונה חיובי ומכנה שלילי (תוצאה שלילית)
        else if (x1 > 0 && x2 < 0) {
            // הופכים את המכנה לערך מוחלט לצורך החישוב
            x2 = minus(0, x2);
            
            // אתחול מחדש לטובת הבלוק
            count = 0; 
            x3 = 0;

            while (count < x1) {
                count = plus(count, x2);
                x3 = plus(x3, 1);
            }

            if (x1 == count) {
                // מחזירים את המונה כשלילי
                return minus(0, x3);
            } else {
                x3 = minus(x3, 1);
                return minus(0, x3);
            }
        }

        // 4. מקרה: מונה שלילי ומכנה חיובי (תוצאה שלילית)
        else if (x1 < 0 && x2 > 0) {
            // יוצרים משתנה חדש עם ערך מוחלט של המונה
            int x1_abs = minus(0, x1);
            
            // אתחול מחדש לטובת הבלוק
            count = 0; 
            x3 = 0;
            
            while (count < x1_abs) {
                count = plus(count, x2);
                x3 = plus(x3, 1);
            }

            if (x1_abs == count) {
                // מחזירים את המונה כשלילי
                return minus(0, x3);
            } else {
                x3 = minus(x3, 1);
                return minus(0, x3);
            }
        }

        // 5. מקרה: מונה שלילי ומכנה שלילי (תוצאה חיובית)
        else { // x1 < 0 ו- x2 < 0
            
            // הופכים את שניהם לערך מוחלט
            x1 = minus(0, x1);
            x2 = minus(0, x2);
            
            // אתחול מחדש לטובת הבלוק
            count = 0; 
            x3 = 0;

            while (count < x1) {
                count = plus(count, x2);
                x3 = plus(x3, 1);
            }

            if (x1 == count) {
                return x3; // מחזירים חיובי
            } else {
                x3 = minus(x3, 1);
                return x3; // מחזירים חיובי
            }
        }
    }
	// Returns x1 % x2
	public static int mod(int x1, int x2) {
		//מקרה שבו אחד אפס 
		if(x2==0){
			System.out.println("Error: Division by zero");
				return 0;
		}
	
		//הגדרת משתנים
		int x3;

		int q = div(x1, x2);


		int p = times(q, x2);

		x3 = x1 - p;

		int res = minus(x1,p);


		return res;
	}	

	public static int sqrt(int x) {
		// מקרה שבו איקס EY
	
		int result=x;
		
		for(int i=1; result>=i; i++){

			result = div(x, i);
		}
		return result;
	}	  	  
}