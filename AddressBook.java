import java.io.*;
import java.util.*;

/**
 * Clase AddressBook que representa una agenda telefónica
 * Almacena contactos en un HashMap y los persiste en un archivo CSV
 * 
 * Actividad 4 - Tema 12
 * Programación en Java
 */
public class AddressBook {
    private HashMap<String, String> contacts;
    private final String fileName;
    private Scanner scanner;

    /**
     * Constructor de la clase AddressBook
     * @param fileName nombre del archivo donde se almacenarán los contactos
     */
    public AddressBook(String fileName) {
        this.contacts = new HashMap<>();
        this.fileName = fileName;
        this.scanner = new Scanner(System.in);
        load(); // Cargar contactos existentes al inicializar
    }

    /**
     * Carga los contactos desde el archivo CSV
     * Formato esperado: numero,nombre
     */
    public void load() {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            int count = 0;
            
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    String[] parts = line.split(",", 2);
                    if (parts.length == 2) {
                        contacts.put(parts[0].trim(), parts[1].trim());
                        count++;
                    }
                }
            }
            
            if (count > 0) {
                System.out.println("[OK] Se cargaron " + count + " contactos desde " + fileName);
            }
            
        } catch (FileNotFoundException e) {
            System.out.println("[INFO] Archivo no encontrado. Se creara uno nuevo: " + fileName);
        } catch (IOException e) {
            System.err.println("[ERROR] Error al leer el archivo: " + e.getMessage());
        }
    }

    /**
     * Guarda los contactos actuales en el archivo CSV
     * Formato: numero,nombre
     */
    public void save() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
            for (Map.Entry<String, String> entry : contacts.entrySet()) {
                writer.println(entry.getKey() + "," + entry.getValue());
            }
            System.out.println("[OK] Contactos guardados exitosamente en " + fileName);
        } catch (IOException e) {
            System.err.println("[ERROR] Error al guardar el archivo: " + e.getMessage());
        }
    }

    /**
     * Muestra todos los contactos de la agenda
     */
    public void list() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("                LISTA DE CONTACTOS");
        System.out.println("=".repeat(60));
        
        if (contacts.isEmpty()) {
            System.out.println("No hay contactos en la agenda.");
            return;
        }

        System.out.print("Contactos: ");
        boolean first = true;
        
        // Ordenar contactos por nombre para mejor presentación
        List<Map.Entry<String, String>> sortedContacts = new ArrayList<>(contacts.entrySet());
        sortedContacts.sort(Map.Entry.comparingByValue());
        
        for (Map.Entry<String, String> entry : sortedContacts) {
            if (!first) {
                System.out.print(" ");
            }
            System.out.print("{" + entry.getKey() + "} : {" + entry.getValue() + "}");
            first = false;
        }
        
        System.out.println("\n" + "=".repeat(60));
        System.out.println("Total de contactos: " + contacts.size());
    }

    /**
     * Crea un nuevo contacto
     */
    public void create() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("               CREAR NUEVO CONTACTO");
        System.out.println("=".repeat(60));
        
        System.out.print("Ingrese el numero telefonico: ");
        String phoneNumber = scanner.nextLine().trim();
        
        if (phoneNumber.isEmpty()) {
            System.out.println("[ERROR] El numero telefonico no puede estar vacio.");
            return;
        }
        
        // Validación básica del número
        if (!isValidPhoneNumber(phoneNumber)) {
            System.out.println("[ERROR] Formato de numero invalido. Use solo numeros, espacios, guiones o parentesis.");
            return;
        }
        
        if (contacts.containsKey(phoneNumber)) {
            System.out.println("[AVISO] Ya existe un contacto con este numero: " + contacts.get(phoneNumber));
            System.out.print("¿Desea actualizar el contacto? (s/n): ");
            String response = scanner.nextLine().trim().toLowerCase();
            
            if (!response.equals("s") && !response.equals("si")) {
                System.out.println("[CANCELADO] Operacion cancelada.");
                return;
            }
        }
        
        System.out.print("Ingrese el nombre del contacto: ");
        String name = scanner.nextLine().trim();
        
        if (name.isEmpty()) {
            System.out.println("[ERROR] El nombre no puede estar vacio.");
            return;
        }
        
        contacts.put(phoneNumber, name);
        save(); // Guardar automáticamente
        
        System.out.println("[OK] Contacto creado/actualizado exitosamente:");
        System.out.println("     " + phoneNumber + " - " + name);
    }

    /**
     * Elimina un contacto de la agenda
     */
    public void delete() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("               ELIMINAR CONTACTO");
        System.out.println("=".repeat(60));
        
        if (contacts.isEmpty()) {
            System.out.println("No hay contactos para eliminar.");
            return;
        }
        
        System.out.print("Ingrese el numero telefonico a eliminar: ");
        String phoneNumber = scanner.nextLine().trim();
        
        if (contacts.containsKey(phoneNumber)) {
            String name = contacts.get(phoneNumber);
            
            System.out.println("Se eliminara el contacto:");
            System.out.println("     " + phoneNumber + " - " + name);
            System.out.print("¿Esta seguro? (s/n): ");
            
            String confirmation = scanner.nextLine().trim().toLowerCase();
            
            if (confirmation.equals("s") || confirmation.equals("si")) {
                contacts.remove(phoneNumber);
                save(); // Guardar automáticamente
                System.out.println("[OK] Contacto eliminado exitosamente.");
            } else {
                System.out.println("[CANCELADO] Eliminacion cancelada.");
            }
        } else {
            System.out.println("[ERROR] No se encontro un contacto con el numero: " + phoneNumber);
        }
    }

    /**
     * Funcionalidad adicional: Buscar contactos
     */
    public void search() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("               BUSCAR CONTACTOS");
        System.out.println("=".repeat(60));
        
        if (contacts.isEmpty()) {
            System.out.println("No hay contactos para buscar.");
            return;
        }
        
        System.out.print("Ingrese el termino de busqueda (nombre o numero): ");
        String searchTerm = scanner.nextLine().trim().toLowerCase();
        
        if (searchTerm.isEmpty()) {
            System.out.println("[ERROR] El termino de busqueda no puede estar vacio.");
            return;
        }
        
        List<Map.Entry<String, String>> results = new ArrayList<>();
        
        for (Map.Entry<String, String> entry : contacts.entrySet()) {
            if (entry.getKey().toLowerCase().contains(searchTerm) || 
                entry.getValue().toLowerCase().contains(searchTerm)) {
                results.add(entry);
            }
        }
        
        if (results.isEmpty()) {
            System.out.println("[ERROR] No se encontraron contactos que coincidan con: " + searchTerm);
        } else {
            System.out.println("[OK] Se encontraron " + results.size() + " resultado(s):");
            for (Map.Entry<String, String> entry : results) {
                System.out.println("     " + entry.getKey() + " - " + entry.getValue());
            }
        }
    }

    /**
     * Valida el formato básico del número telefónico
     */
    private boolean isValidPhoneNumber(String phoneNumber) {
        // Permite números, espacios, guiones, paréntesis y el signo +
        return phoneNumber.matches("[\\d\\s\\-\\(\\)\\+]+");
    }

    /**
     * Muestra el menú principal y maneja la interacción con el usuario
     */
    public void showMenu() {
        boolean running = true;
        
        System.out.println("=========================================");
        System.out.println("    BIENVENIDO A LA AGENDA TELEFONICA    ");
        System.out.println("=========================================");
        
        while (running) {
            System.out.println("\n" + "=".repeat(40));
            System.out.println("           MENU PRINCIPAL");
            System.out.println("=".repeat(40));
            System.out.println("1. Listar contactos");
            System.out.println("2. Crear contacto");
            System.out.println("3. Eliminar contacto");
            System.out.println("4. Buscar contactos");
            System.out.println("5. Guardar cambios");
            System.out.println("6. Recargar desde archivo");
            System.out.println("0. Salir");
            System.out.println("=".repeat(40));
            System.out.print("Seleccione una opcion: ");
            
            String option = scanner.nextLine().trim();
            
            switch (option) {
                case "1":
                    list();
                    break;
                case "2":
                    create();
                    break;
                case "3":
                    delete();
                    break;
                case "4":
                    search();
                    break;
                case "5":
                    save();
                    break;
                case "6":
                    load();
                    break;
                case "0":
                    System.out.println("\n¡Gracias por usar la Agenda Telefonica!");
                    System.out.println("Los cambios se han guardado automaticamente.");
                    running = false;
                    break;
                default:
                    System.out.println("[ERROR] Opcion no valida. Seleccione una opcion del 0 al 6.");
            }
            
            if (running) {
                System.out.println("\nPresione Enter para continuar...");
                scanner.nextLine();
            }
        }
        
        scanner.close();
    }

    /**
     * Método main - Punto de entrada del programa
     */
    public static void main(String[] args) {
        String fileName = "contactos.csv";
        
        // Permitir especificar un archivo diferente como argumento
        if (args.length > 0) {
            fileName = args[0];
        }
        
        AddressBook addressBook = new AddressBook(fileName);
        addressBook.showMenu();
    }
}