package com.company;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.io.*;
        public class Main {
            public static void main(String[] args) throws IOException {
                Scanner input = new Scanner(System.in);
                FileWriter arqtxt = new FileWriter("contas.txt");
                PrintWriter saveArq = new PrintWriter(arqtxt);
                Hash hash = Hash.getInstance();
                String x = "Bom dia flor do dia";
                String saida = hash.toString(x);

                System.out.println(saida);
                System.out.println("*~~~~~~~~~~~~~~~~~~~~~~~~*");
                System.out.println("Escolha a opção desejada?");
                System.out.println("1 - Cadastrar conta");
                System.out.println("2 - Autenticar conta");
                System.out.print("Escolha a opção correspondente -> ");
                int opcao = input.nextInt();

                if (opcao == 1 ) {
                        System.out.print("Cadastre um login de 4 caracteres -> ");
                        String login = input.next();
                        System.out.print("Cadastre uma senha de 4 caracteres -> ");
                        String senha = input.next();
                        String senhaMD5 = hash.toString(senha);
                        saveArq.append(String.format("\n%s | %s\n", login, senhaMD5));
                        arqtxt.close();
                        System.out.print("Cadastro concluído!");
                }
                else if (opcao == 2){
                    BufferedReader br1 = new BufferedReader(new InputStreamReader(new FileInputStream("contas.txt"),"UTF-8"));
                    String line;
                    while ((line = br1.readLine()) != null ){
                        {
                            line.split("|");
                        }
                        br1.close();
                    System.out.print("Autenticação concluída!");
                    }
                }
                else {
                    System.out.print("Opção inválida!");
                }
            }
        }
