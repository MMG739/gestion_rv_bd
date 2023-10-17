package com.ism;

import com.ism.entities.Medecin;
import com.ism.entities.Patient;
import com.ism.entities.Rv;
import com.ism.repositories.bd.MedecinRepository;
import com.ism.repositories.bd.PatientRepository;
import com.ism.repositories.bd.RvRepository;
import com.ism.repositories.bd.impl.MedecinRepositoryImpl;
import com.ism.repositories.bd.impl.PatientRepositoryImpl;
import com.ism.repositories.bd.impl.RvRepositoryImpl;
import com.ism.repositories.core.Database;
import com.ism.repositories.core.MySQLImpl;
import com.ism.services.MedecinServiceImpl;
import com.ism.services.PatientServiceIpml;
import com.ism.services.RvServiceImpl;


import java.text.ParseException;
import java.util.Date;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws ParseException {
        Database database=new MySQLImpl();
        //Repo
        PatientRepository patientRepository=new PatientRepositoryImpl(database);
        PatientServiceIpml patientServiceIpml=new PatientServiceIpml(patientRepository);

        MedecinRepository medecinRepository=new MedecinRepositoryImpl(database);
        MedecinServiceImpl  medecinServiceIpml=new MedecinServiceImpl(medecinRepository);

        RvRepository rvRepository=new RvRepositoryImpl(database, medecinRepository, patientRepository);
        RvServiceImpl rvServiceIpml=new RvServiceImpl(rvRepository);

        int choice;
        Scanner scanner=new Scanner(System.in);
        do {
            System.out.println("Menu");
            System.out.println("1. Add Patient");
            System.out.println("2. Add Medecin");
            System.out.println("3. Planifier Rv");
            System.out.println("4. Afficher Rv du jour");
            System.out.println("5. Exit");
            System.out.println("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    System.out.println("Add Patient");
                    System.out.println("Enter  name: ");
                    String patient_name = scanner.nextLine();
                    System.out.println("Enter antecedents");
                    String antecedent = scanner.nextLine();

                    Patient patient=new Patient(patient_name,antecedent);
                    patientServiceIpml.add(patient);

                    System.out.println("ajout reussi");
                    patientServiceIpml.getAll().forEach(System.out::println);


                    break;
                case 2:
                    System.out.println("Add Medecin");
                    System.out.println("Enter  name: ");
                    String medecin_name = scanner.nextLine();
                    System.out.println("Enter antecedents");
                    String speciality = scanner.nextLine();

                    Medecin  medecin=new Medecin(medecin_name,speciality);
                    medecinServiceIpml.add(medecin);

                    System.out.println("ajout reussi");
                    medecinServiceIpml.getAll().forEach(System.out::println);
                    break;
                case 3:
                    System.out.println("Plannifier Rv");
                    System.out.println("Enter patient id: ");
                    patientServiceIpml.getAll().forEach(System.out::println);
                    Patient rv_patient=patientServiceIpml.show(scanner.nextInt());
                    scanner.nextLine();
                    System.out.println("Enter medecin id: ");
                    medecinServiceIpml.getAll().forEach(System.out::println);
                    Medecin rv_medecin=medecinServiceIpml.show(scanner.nextInt());
                    scanner.nextLine();
                    System.out.println("Enter date: dd/MM/yyyy ");
                    Date date=rvServiceIpml.dateFormat(scanner.nextLine());

                    Rv rv=new Rv(date,rv_medecin,rv_patient);
                    rvServiceIpml.add(rv);
                    System.out.println("ajout reussi");
                    rvServiceIpml.getAll().forEach(System.out::println);

                    break;
                case 4:
                    System.out.println("Liste des Rv du jour");
                    rvServiceIpml.todayRv().forEach(System.out::println);
                    break;
                case 5:
                    System.out.println("Exit");
                    break;
                default:
                    System.out.println("Invalid choice");
                    break;
            }
        }while (choice!=5);




    }
}