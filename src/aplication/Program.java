package aplication;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

import model.entities.Contract;
import model.entities.Installment;
import model.services.ContractService;
import model.services.PaypalService;

public class Program {

	public static void main(String[] args) {
		DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Entre os dados do contrato:");
		System.out.print("Numero: ");
		int numero = sc.nextInt();
		
		System.out.print("Data (dd/MM/YYYY): ");
		sc.nextLine();
		LocalDate data = LocalDate.parse(sc.next(),fmt);
		
		System.out.print("Valor do contrato: ");
		double valorContrato = sc.nextDouble();
		
		System.out.print("Entre com o numero de parcelas: ");
		int parcelas = sc.nextInt();
		
		Contract contract = new Contract(numero, data, valorContrato);
		
		ContractService service = new ContractService(new PaypalService());
		
		service.processContract(contract, parcelas);
		
		System.out.println("Parcelas:");
		
		for(Installment x : contract.getList()) {
			System.out.println(x.toString());
		}
		
		sc.close();

	}

}
