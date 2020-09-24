package aplicacao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Collectors;

import entidades.Funcionario;

public class Programa {

	public static void main (String [] args) {
		
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Entre com o caminho: ");
		String path = sc.next();
		
		try (BufferedReader br = new BufferedReader(new FileReader(path))) {
			
			List<Funcionario> lista = new ArrayList<>();
		
			String linha = br.readLine();
			
			while(linha!=null) {
				
				String[] fields = linha.split(",");
				
				lista.add(new Funcionario(fields[0], fields[1], Double.parseDouble(fields[2])));
				
				linha = br.readLine();		
			}
			
			System.out.print("Entre com um salário: ");
			Double limite = sc.nextDouble();
			
			List<String> salarios = lista.stream()
					.filter(p -> p.getSalario() > limite)
					.map(p -> p.getEmail())
					.sorted() 
					.collect(Collectors.toList());
			
			System.out.println("Email de todos que possuem salário maior que "+limite+":");
			salarios.forEach(System.out :: println);
						
			double soma = lista.stream()
					.filter(p -> p.getNome().charAt(0) == 'M')
					.map(p -> p.getSalario())
					.reduce(0.0, (x,y) -> x+y);
			
			System.out.print("Soma do salário de todos que possuem o nome iniciado com 'M': " + soma);
				
		}catch (IOException e){
			System.out.println("Erro: " + e.getMessage());
		}
		
		sc.close();	
	}
}
