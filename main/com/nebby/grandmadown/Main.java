package com.nebby.grandmadown;

import java.io.IOException;

import com.nebby.grandmadown.network.ClientNetwork;

public class Main 
{
	
	public static void main(String[] args) throws IOException
	{
		ClientNetwork network = new ClientNetwork();
		try
		{
			network.connect("ec2-54-172-226-18.compute-1.amazonaws.com", 8888);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
		network.update();
		System.out.println("VALIDATING!");
		network.validate(true);
		System.out.println("SECURED!");
	}

}
