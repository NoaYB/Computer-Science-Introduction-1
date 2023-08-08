
public class Assignment2 {

	/*-----------------------
	 *| Part A - tasks 1-11 |
	 * ----------------------*/
	
	// task 1
	public static boolean isSquareMatrix(boolean[][] matrix)
	{
		if (matrix == null) {return false;}
		else if (matrix.length == 0) {return false;}
		else
		{
			for (boolean[] i : matrix) {if (i.length != matrix.length) {return false;}}	
			return true;
		}
	}
	
	// task 2
	public static boolean isSymmetricMatrix(boolean[][] matrix)
	{
		if (matrix.length == 0) {return false;}
		else
		{
			int countForI = 0;
			for (boolean[] i : matrix)
			{
				int countForJ = 0;
				for (boolean j : i)
				{
					if (matrix[countForI][countForJ] != matrix[countForJ][countForI])
					{
						return false;
					}
					countForJ = countForJ + 1;
				}
				countForI = countForI + 1;
			}
			return true;
		}
	}

	// task 3
	public static boolean isAntiReflexiveMatrix(boolean[][] matrix)
	{
		if (matrix.length == 0) {return false;}
		else
		{
			for (int i = 0; i <= (matrix.length - 1); i = i + 1)
			{
				if (matrix[i][i]) {return false;}
			}
			return true;
		}	
	}
	
	// task 4
	public static boolean isLegalInstance(boolean[][] matrix)
	{
		if (matrix == null) {return false;}
		else if (isSquareMatrix(matrix) & isSymmetricMatrix(matrix) & isAntiReflexiveMatrix(matrix)) {return true;}
		else {return false;}		
	}
	
	// task 5
	public static boolean isPermutation(int[] array)
	{
		for (int j = 0; j <= (array.length - 1); j = j + 1)
		{
			int count = 0;
			for (int i = 0; i <= (array.length - 1); i = i + 1)
			{
				if (array[i] == j) {count = count + 1;}
			}
			if (count != 1) {return false;}
		}
		return true;	
	}
	
	// task 6
	public static boolean hasLegalSteps(boolean[][] flights, int[] tour)
	{
		for (int i = 0; i < (tour.length - 1); i = i + 1)
		{
			if (!flights[tour[i]][tour[i+1]]) {return false;}
		}
		if (!flights[tour[0]][tour[tour.length - 1]]) {return false;}
		return true;		
	}
	
	// task 7
	public static boolean isSolution(boolean[][] flights, int[] tour)
	{
		if (tour == null) {throw new UnsupportedOperationException("The array (tour) is empty");}
		else if (tour.length != flights.length) {throw new UnsupportedOperationException("The length of the array (tour) is not the appropriate length");}
		else if (isPermutation(tour) & (tour[0] == 0) & (hasLegalSteps(flights,tour))) {return true;}
		else {return false;}
	}
	
	// task 8
	public static boolean evaluate(int[][] cnf, boolean[] assign)
	{
		int NumberOfVariables = (assign.length - 1); 	
		for (int[] clauseInt : cnf)
		{
			int index = 0;
			boolean[] clauseBoolean = new boolean[clauseInt.length];
			for (int j = 0; j < clauseInt.length; j = j + 1)
			{
				boolean Continue = true;
				for (int n = 1; n <= NumberOfVariables & Continue; n = n + 1)
				{
					if (clauseInt[j] == n)
					{clauseBoolean[index] = assign[n]; index = index + 1; Continue = false;}
					else if (clauseInt[j] == -n)
					{clauseBoolean[index] = !assign[n]; index = index + 1; Continue = false;}
				}						
			}		
			boolean isTrue = false;
			for (boolean m : clauseBoolean)
			{
				if (m) {isTrue = true;}
			}
			
			if (!isTrue) {return false;}	
		}
		return true;	
	}
	
	// task 9
	public static int[][] atLeastOne(int[] lits)
	{
		int NumberOfVariables = lits.length;
		int[][] cnf = new int[1][NumberOfVariables];
		
		for (int[] clause : cnf)
		{
			for (int j = 0; j < clause.length; j = j + 1)
			{
				cnf[0][j] = lits[j];
			}
		}
		return cnf;		
	}

	// task 10
	public static int[][] atMostOne(int[] lits)
	{
		int NumberOfVariables = lits.length;
		int NumberOfClauses = NumberOfVariables * (NumberOfVariables - 1) / 2;
		int NumberOfVariablesInClause = 2;
		int index = 0;
		
		int cnf[][] = new int[NumberOfClauses][NumberOfVariablesInClause];
		
		for (int i = 0; i < (NumberOfVariables - 1); i = i + 1)
		{
			for (int j = i + 1; j < (NumberOfVariables); j = j + 1, index = index + 1)
			{
				int[] clause = {-lits[i], -lits[j]};
				cnf[index] = clause;
			}
		}
		return cnf;				
	}
	
	// task 11
	public static int[][] exactlyOne(int[] lits)
	{
		int[][] cnf1 = atMostOne(lits);
		int[][] cnf2 = atLeastOne(lits);
		int[][] cnf = new int[cnf1.length + cnf2.length][];
		int count = 0;
		
		for (int i = 0; i < cnf1.length; i++)
		{
			cnf[count] = cnf1[i];
			count = count + 1;
		}
		
		for (int j = 0; j < cnf2.length; j++)
		{
			cnf[count] = cnf2[j];
			count = count + 1;
		}		
		return cnf;
	}
	
	/*------------------------
	 *| Part B - tasks 12-20 |
	 * -----------------------*/
	
	// task 12a
	public static int map(int i, int j, int n)
	{
		int map = 0;
		int countForKoutside = 1 + i*n;		
		for (int countForI = 0; countForI <= i; countForI = countForI + 1)
		{
			int countForKinside = 0;
			for (int countForJ = 0; countForJ <= j; countForJ = countForJ + 1)
			{
				if ((countForI == i) & (countForJ == j)) {map =  countForKoutside + countForKinside;}
				countForKinside = countForKinside + 1;
			}
		}
		return map;		
	}
	
	// task 12b
	public static int[] reverseMap(int k, int n)
	{
		int[] reverseMap = {0, 0};
		int countForI = 0;
		for (int countForK = 1; countForK <= n*n; countForK = countForK + 1)
		{		
			for (int countForJ = 0; countForJ < n; countForJ = countForJ + 1)
			{
				if (map(countForI,countForJ,n) == k) {int[] array = {countForI, countForJ}; reverseMap = array;}
			}
			countForI = countForI + 1;
		}
		return reverseMap;
	}
	
	// task 13
	public static int[][] oneCityInEachStep(int n)
	{
		int[][] lits = new int[n][n];
		int[][] cnf = new int[n * ((n*(n-1)/2)+1)][];
		int[][][] cnfs = new int[n][(n*(n-1)/2)+1][];
		int count = 0;
		
		for (int i = 0; i < n; i = i + 1)
		{
			for (int j = 0; j < n; j = j + 1)
			{
				lits[i][j] = map(i,j,n);
			}
			cnfs[i] = exactlyOne(lits[i]);
		}
		
		for (int a = 0; a < n; a = a + 1)
		{
			for (int b = 0; b < ((n*(n-1)/2)+1); b = b + 1)
			{
				cnf[count] = cnfs[a][b];
				count = count + 1;
			}
		}
		return cnf;		
	}

	// task 14
	public static int[][] eachCityIsVisitedOnce(int n)
	{
		int[][] lits = new int[n][n];
		int[][] cnf = new int[n * ((n*(n-1)/2)+1)][];
		int[][][] cnfs = new int[n][(n*(n-1)/2)+1][];
		int count = 0;
		
		for (int j = 0; j < n; j = j + 1)
		{
			for (int i = 0; i < n; i = i + 1)
			{
				lits[j][i] = map(i,j,n);
			}
			cnfs[j] = exactlyOne(lits[j]);
		}
		
		for (int a = 0; a < n; a = a + 1)
		{
			for (int b = 0; b < ((n*(n-1)/2)+1); b = b + 1)
			{
				cnf[count] = cnfs[a][b];
				count = count + 1;
			}
		}
		return cnf;	
	}
	
	// task 15
	public static int[][] fixSourceCity(int n)
	{
		int[][] cnf = {{map(0,0,n)}};
		return cnf;			
	}
	
	// task 16
	public static int[][] noIllegalSteps(boolean[][] flights)
	{
		int n = flights.length;
		int count = 0;
		int index = 0;
		
		for (int j = 0; j < n; j = j + 1)
		{
			for (int k = 0; k < n; k = k + 1)
			{
				if ((!flights[j][k]) & (j != k)) {count = count + 1;}
			}
		}
			
		int[][] cnf = new int[count * n][2];
		
		for (int i = 0; i < n; i = i + 1)
		{
			for (int j = 0; j < n; j = j + 1)
			{
				for (int k = 0; k < n; k = k + 1)
				{
					if ((!flights[j][k]) & (j != k))
					{ 
						int[] clause = {-map(i,j,n), -map((i + 1)%n,k,n)};
						cnf[index] = clause;
						index = index + 1;
					}
				}
			}
		}
		if (count == 0) {cnf = new int[0][0];}
		return cnf;		
	}
	
	// task 17
	public static int[][] encode(boolean[][] flights)
	{
		int[][] cnf1 = oneCityInEachStep(flights.length);
		int[][] cnf2 = eachCityIsVisitedOnce(flights.length);	
		int[][] cnf3 = fixSourceCity(flights.length);
		int[][] cnf4 = noIllegalSteps(flights);
		int[][] cnf = new int[cnf1.length + cnf2.length + cnf3.length + cnf4.length][];
		
		int count = 0;
		
		for (int i = 0; i < cnf1.length; i++)
		{
			cnf[count] = cnf1[i];
			count = count + 1;
		}
		
		for (int i = 0; i < cnf2.length; i++)
		{
			cnf[count] = cnf2[i];
			count = count + 1;
		}
		
		for (int i = 0; i < cnf3.length; i++)
		{
			cnf[count] = cnf3[i];
			count = count + 1;
		}
		
		for (int i = 0; i < cnf4.length; i++)
		{
			cnf[count] = cnf4[i];
			count = count + 1;
		}
		
		return cnf;				
	}

	// task 18
	public static int[] decode(boolean[] assignment, int n)
	{
		if (assignment == null) {throw new UnsupportedOperationException("The array (assignment) is null");}
		else if (assignment.length != n*n + 1) {throw new UnsupportedOperationException("The length of the array (assignment) is not the appropriate length");}
		
		int[] tour = new int[n];
		
		for (int i = 0; i < n; i = i + 1)
		{
			for (int j = 0; j < n; j = j + 1)
			{
				if(assignment[map(i,j,n)]) {tour[i] = j;}
			}
		}
		return tour;		
	}
	
	// task19
	public static int[] solve(boolean[][] flights)
	{
		if (!isLegalInstance (flights)) {throw new UnsupportedOperationException("Invalid phase");}
		
		int nVars = flights.length;
		int[] tour = new int[nVars];
		SATSolver.init(nVars * nVars);
		
		int[][] cnf = encode(flights);
		SATSolver.addClauses(cnf);
	
		boolean[] assignment = SATSolver.getSolution();
			
		if (assignment == null) {throw new UnsupportedOperationException("Timeout");} 		
		else if (assignment.length == nVars*nVars+1)
		{
			tour = decode(assignment, nVars);
			if (!isSolution(flights,tour)) {throw new UnsupportedOperationException("Invalid solution");}
		}
		else {tour = null;}
		
		return tour;			
	}
	
	// task20
	public static boolean solve2(boolean[][] flights)
	{
		if (!isLegalInstance (flights)) {throw new UnsupportedOperationException("Invalid phase");}
		
		boolean value = true;
		int[] count = new int[flights.length];
		
		for (int i = 0; i < flights.length; i++)
		{
			for (int j = 0; j < flights.length; j++)
			{
				if (flights[i][j]) {count[i] = count[i] + 1;}
			}
		}
		//Counts the amount of TRUE by line in the array
			
		if (flights.length == 4)
		{
			for (int i : count)
			{
				if (i < 3) {value = false;}				
			}
		}
		//Checks if all destinations have 3 flights to different cities
		
		else if (flights.length > 4)
		{
			int countFor2 = 0;
			int countFor3orBigger = 0;
			for (int i : count)
			{
				if (i >= 3) {countFor3orBigger = countFor3orBigger + 1;}
				else if (i == 2) {countFor2 = countFor2 + 1;}
			}
			if (!((countFor3orBigger >= 4) & (countFor2 == flights.length - countFor3orBigger))) {value = false;}
		}
		//Checks if there are at least 4 different cities with 3 or more flights to different cities
		
		else {value = false;}
		
		return value;		
	}
		
}
