import AccesoADatos.RolDAO;
import Entidades.Rol;
import java.util.List;//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        RolDAO dao = new RolDAO();
        List<Rol> roles = dao.obtenerRoles();

        for (Rol rol : roles) {
            System.out.println("ID:" + rol.getIdRol() +
                    ",Nombre:" + rol.getNombre() +
                    ",Estados:" + rol.getEstado());

        }

    }
}