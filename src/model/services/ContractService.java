package model.services;

import java.time.LocalDate;

import model.entities.Contract;
import model.entities.Installment;

public class ContractService {
	private OnlinePaymentService onlinePaymentService;
	
	public ContractService(OnlinePaymentService onlinePaymentService) {
		this.onlinePaymentService = onlinePaymentService;
	}

	public void processContract(Contract contract, int months) {
		for(int i = 1; i <= months; ++i) {
			LocalDate date = contract.getDate();
			LocalDate installmentDate = date.plusMonths(i);
			double installmentValue = contract.getTotalValue() / months;
			double interest = onlinePaymentService.interest(installmentValue, i);
			double amount = installmentValue + interest;
			double fee = onlinePaymentService.paymentFee(amount);
			amount += fee;
			contract.addList(new Installment(installmentDate, amount));
		}
	}
}
