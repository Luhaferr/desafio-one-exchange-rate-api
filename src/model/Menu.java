package model;

import api.ExchangeRateApi;

import java.util.Scanner;

public class Menu {
    Scanner scanner = new Scanner(System.in);
    private ExchangeRateApi exchangeRateApi;
    private Currency currency;
    private double amount;
    public Menu(){
        this.exchangeRateApi = new ExchangeRateApi();
        System.out.println("*******************************************");
        System.out.println("Seja bem-vindo/a ao Conversor de Moeda");
        System.out.println("*******************************************\n");
    }
    public void startMenu() {
        boolean running = true;

        while (running) {
            System.out.println("Escolha uma das opções abaixo:\n");
            System.out.println("1) Dólar => Peso argentino");
            System.out.println("2) Peso argentino => Dólar");
            System.out.println("3) Dólar => Real brasileiro");
            System.out.println("4) Real brasileiro => Dólar");
            System.out.println("5) Dólar => Peso colombiano");
            System.out.println("6) Peso colombiano => Dólar");
            System.out.println("7) Sair");
            System.out.println("*******************************************\n");

            int option = scanner.nextInt();

            if (option == 7) {
                running = false;
                System.out.println("Encerrando...");
            } else if (option >= 1 && option <= 6) {
                System.out.println("Digite o valor que deseja converter:");
                amount = scanner.nextDouble();
                processConversion(option, amount);
            } else {
                System.out.println("Opção inválida!");
            }
        }
    }

    private void processConversion(int option, double amount) {
        String fromCurrency = "";
        String toCurrency = "";

        switch (option) {
            case 1:
                fromCurrency = "USD";
                toCurrency = "ARS";
                break;
            case 2:
                fromCurrency = "ARS";
                toCurrency = "USD";
                break;
            case 3:
                fromCurrency = "USD";
                toCurrency = "BRL";
                break;
            case 4:
                fromCurrency = "BRL";
                toCurrency = "USD";
                break;
            case 5:
                fromCurrency = "USD";
                toCurrency = "COP";
                break;
            case 6:
                fromCurrency = "COP";
                toCurrency = "USD";
                break;
        }

        this.currency = exchangeRateApi.convertValue(fromCurrency, toCurrency, amount);
        convertionMessage();
    }

    private void convertionMessage() {
        System.out.println(amount + " [" + currency.base_code() + "] corresponde ao valor final de =>> " + currency.conversion_result() + " [" + currency.target_code() + "]");
        System.out.println("*******************************************\n");
    }

}
