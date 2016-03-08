import java.util.Scanner;
import java.text.NumberFormat;

public class Invoice {

	static Scanner sc = new Scanner(System.in);
	static NumberFormat currency = NumberFormat.getCurrencyInstance();
	static double taxRate = 0;
	static final double defaultTaxRate = 0.05;
	static final int maxNumberOfPrices = 10;
	static double[] prices = new double[maxNumberOfPrices];

	public static void main(String[] args)
	{
		getTaxRate();
		getPrices();
		sc.close();
	}

	private static void getTaxRate()
	{
		try
		{
			System.out.print("Tax Rate?:\t");
			taxRate = sc.nextDouble();
		}
		catch(java.util.InputMismatchException e)
		{
			System.out.println("Invalid Input. Tax rate should be a number.");
			System.out.println("Tax rate set to default value: " + defaultTaxRate);
			taxRate = defaultTaxRate;
		}
		finally
		{
			sc.nextLine();
		}
	}

	private static void getPrices()
	{
		int counter = 0;
		double inputPrice = 1.0;//initialize to a nonzero value
		while(inputPrice != 0.0)
		{
			try
			{
				System.out.printf("Price #%d:\t", counter + 1);
				inputPrice = sc.nextDouble();
			}
			catch(java.util.InputMismatchException e)
			{
				System.out.println("Invalid Input. Prices should be numbers.");
				System.out.println("Ending price entry.");
				inputPrice = 0;
			}
			finally
			{
				sc.nextLine();
			}
			if(inputPrice == 0.0)
			{
				display(counter);
			}
			else
			{
				try
				{
					prices[counter] = inputPrice;
					counter++;
				}
				catch(ArrayIndexOutOfBoundsException e)
				{
					System.out.println("You have entered more prices than the maximum this application can handle.");
					inputPrice = 0;
				}
				catch(Exception e)
				{
					System.out.println("An error has occurred.");
				}
			}
		}
	}

	private static void display(int count)
	{
		double subtotal = 0.0;
		System.out.println("\n--------------------------------------------\n");
		System.out.println("Receipt");
		if(count != 0)
		{
			for(int i = 0; i < count; i++)
			{
				System.out.println(currency.format(prices[i]));
				subtotal += prices[i];
			}
		}
		double totalTax = subtotal * taxRate;
		double total = subtotal + totalTax;
		System.out.printf("%s subtotal%n", currency.format(subtotal));
		System.out.printf("%s tax%n", currency.format(totalTax));
		System.out.printf("%s grand total%n", currency.format(total));
	}
}
