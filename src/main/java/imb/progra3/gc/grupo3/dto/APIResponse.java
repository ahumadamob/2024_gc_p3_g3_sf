package imb.progra3.gc.grupo3.dto;

import java.util.List;

public class APIResponse<T> {
    private int status;
    private List<String> mensajes;
    private T data;

    public APIResponse(int status, List<String> mensajes, T data) {
        this.status = status;
        this.mensajes = mensajes;
        this.data = data;
    }

    // Getters y setters
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<String> getMensajes() {
        return mensajes;
    }

    public void setMensajes(List<String> mensajes) {
        this.mensajes = mensajes;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}

