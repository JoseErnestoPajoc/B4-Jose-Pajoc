package ejemplo.proyecto.pkg4to;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class ventana extends JFrame {

    JPanel panelInicioSesion, panelNuevoUsuario, panelClientes;
    JTextField txtUsuario;
    JPasswordField txtContra;
    usuario misUsuarios[] = new usuario[10];

    public ventana() {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
        misUsuarios[0] = new usuario("admin", "123");
        misUsuarios[1] = new usuario("otro", "321");
    }

    public void inciarComponentes() {
        panelInicioSesion = new JPanel();
        panelInicioSesion.setLayout(null);
        this.getContentPane().add(panelInicioSesion);
        componentesInicioSesion();

        panelNuevoUsuario = new JPanel();
        panelNuevoUsuario.setLayout(null);
        this.getContentPane().add(panelNuevoUsuario);
        panelNuevoUsuario.setVisible(false);
        
        panelClientes = new JPanel();
        panelClientes.setLayout(null);
        this.getContentPane().add(panelClientes);
        panelClientes.setVisible(false);
    }

    public void componentesInicioSesion() {
        this.setTitle("Inicio Sesión");
        JLabel lblUsuario = new JLabel("Ingrese su usuario");
        lblUsuario.setBounds(50, 15, 150, 15);
        panelInicioSesion.add(lblUsuario);

        JLabel lblContra = new JLabel("Ingrese su password");
        lblContra.setBounds(50, 50, 150, 15);
        panelInicioSesion.add(lblContra);

        txtUsuario = new JTextField();
        txtUsuario.setBounds(210, 15, 150, 20);
        panelInicioSesion.add(txtUsuario);

        txtContra = new JPasswordField();
        txtContra.setBounds(210, 50, 150, 20);
        panelInicioSesion.add(txtContra);

        JButton btnIniciar = new JButton("Ingresar");
        btnIniciar.setBounds(100, 150, 100, 30);
        panelInicioSesion.add(btnIniciar);

        ActionListener iniciarSesion = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String usuario = txtUsuario.getText();
                String contra = txtContra.getText();
                buscarUsuario(usuario, contra);
            }
        };

        btnIniciar.addActionListener(iniciarSesion);

        //--------------------------------------->  botón nuevo usuario
        JButton btnNuevoUsuario = new JButton("Registrar");
        btnNuevoUsuario.setBounds(250, 150, 100, 30);
        panelInicioSesion.add(btnNuevoUsuario);
        ActionListener nuevoUsuario = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                componentesNuevoUsuario();
                panelInicioSesion.setVisible(false);
                panelNuevoUsuario.setVisible(true);
            }
        };
        btnNuevoUsuario.addActionListener(nuevoUsuario);

        panelInicioSesion.repaint();
    }

    public void buscarUsuario(String usuario, String contra) {
        boolean encontrado = false;
        String nombre = "";
        for (int i = 0; i <= 9; i++) {
            if (misUsuarios[i] != null) {
                if (misUsuarios[i].getNombre().equals(usuario) && misUsuarios[i].getContra().equals(contra)) {
                    encontrado = true;
                    nombre = misUsuarios[i].getNombre();
                    break;
                }
            }
        }

        if (encontrado) {
            JOptionPane.showMessageDialog(null, "Bienvenido " + nombre);
        } else {
            JOptionPane.showMessageDialog(null, "Datos incorrectos");
        }
    }

    public void componentesNuevoUsuario() {
        this.setTitle("Crear nueva cuenta");
        JLabel nuevoNombre = new JLabel("Ingrese nombre de usuario: ");
        nuevoNombre.setBounds(80, 25, 170, 20);
        panelNuevoUsuario.add(nuevoNombre);

        JLabel nuevaContra = new JLabel("Ingrese password: ");
        nuevaContra.setBounds(80, 75, 170, 20);
        panelNuevoUsuario.add(nuevaContra);

        JTextField txtNombreNuevo = new JTextField();
        txtNombreNuevo.setBounds(250, 25, 150, 20);
        panelNuevoUsuario.add(txtNombreNuevo);

        JPasswordField txtcontraNueva = new JPasswordField();
        txtcontraNueva.setBounds(250, 75, 150, 20);
        panelNuevoUsuario.add(txtcontraNueva);

        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.setBounds(150, 180, 100, 30);
        panelNuevoUsuario.add(btnGuardar);
        ActionListener almacenar = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = txtNombreNuevo.getText();
                String contra = txtcontraNueva.getText();

                if (guardarUsuario(nombre, contra)) {
                    txtNombreNuevo.setText("");
                    txtcontraNueva.setText("");
                }

            }
        };

        btnGuardar.addActionListener(almacenar);

        JButton btnVolver = new JButton("Volver");
        btnVolver.setBounds(300, 180, 100, 30);
        panelNuevoUsuario.add(btnVolver);
        ActionListener volver = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                componentesInicioSesion();
                panelInicioSesion.setVisible(true);
                panelNuevoUsuario.setVisible(false);
            }
        };

        btnVolver.addActionListener(volver);

    }

    public boolean guardarUsuario(String nombre, String contra) {
        boolean guardado = false;
        if (nombre.equals("") || contra.equals("")) {
            JOptionPane.showMessageDialog(null, "Debe llenar todos los campos");
        } else {
            if (comprobarDuplicadoUsuario(nombre)) {
                JOptionPane.showMessageDialog(null, "Ya existe el usuario");
            } else {
                boolean vacio = false;
                int posicion = -1;
                for (int i = 0; i <= 9; i++) {
                    if (misUsuarios[i] == null) {
                        vacio = true;
                        posicion = i;
                        break;
                    }
                }
                if (vacio) {
                    misUsuarios[posicion] = new usuario(nombre, contra);
                    JOptionPane.showMessageDialog(null, "Usuario almacenado");
                    guardado = true;
                } else {
                    JOptionPane.showMessageDialog(null, "Ya no se puden guardar más usuarios");
                }
            }
        }
        return guardado;
    }

    public boolean comprobarDuplicadoUsuario(String nombre) {
        boolean duplicado = false;
        for (int i = 0; i <= 9; i++) {
            if (misUsuarios[i] != null) {
                if (misUsuarios[i].getNombre().equals(nombre)) {
                    duplicado = true;
                    break;
                }
            }
        }
        return duplicado;
    }
    
    //------------------- Componentes para mostrar los clientes
    

}
