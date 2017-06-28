package com.firm.derbyproject.main;

import java.util.Scanner;

import com.firm.derbyproject.backend.GenericCRUDOperation;

public class DerbyAppDemo {

	private static final String exitCommand = "exit";
	private static final String insertCommand = "insert";
	private static final String deleteCommand = "delete";
	private static final String updateCommand = "update";
	private static final String dropCommand = "drop";
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String line = null;
		GenericCRUDOperation crudOperation = new GenericCRUDOperation();
		System.out.println("Insert a sql command: ");
		while (!((line = scanner.nextLine()).toLowerCase()).equalsIgnoreCase(exitCommand)) {
			String commandSql = line.toLowerCase();
			if (commandSql.contains("create") 
					|| commandSql.contains(insertCommand)
					|| commandSql.contains(deleteCommand) 
					|| commandSql.contains(updateCommand) 
					|| commandSql.contains(dropCommand)) {
				crudOperation.modifyDataOrTable(commandSql);
			} else {
				System.out.println("--------------");
				for (Object object : crudOperation.retrieveData(commandSql)) {
					Object[] row = (Object[]) object;

					for (Object obj : row) {
						System.out.print(obj.toString() + " ");
					}
					System.out.println();

				}
				System.out.println("--------------");
			}
			System.out.println("Insert a sql command: ");
		}

	}

}
