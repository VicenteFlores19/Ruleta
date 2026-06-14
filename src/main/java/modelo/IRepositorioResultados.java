package modelo;
import java.util.List;

public interface IRepositorioResultados {
    void guardar(Resultado resultado);
    List<Resultado> obtenerTodos();
}