package imb.progra3.gc.grupo3.controller;

import imb.progra3.gc.grupo3.entity.Cliente;
import imb.progra3.gc.grupo3.service.IClienteService;
import imb.progra3.gc.grupo3.util.APIResponse;
import imb.progra3.gc.grupo3.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    @Autowired
    private IClienteService clienteService;

    @GetMapping
    public ResponseEntity<APIResponse<List<Cliente>>> getAll() {
        List<Cliente> clientes = clienteService.getAll();
        return ResponseUtil.success(clientes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<APIResponse<Cliente>> getById(@PathVariable Long id) {
        Cliente cliente = clienteService.getById(id);
        return cliente != null ? ResponseUtil.success(cliente) : ResponseUtil.notFound("Cliente no encontrado");
    }

    @PostMapping
    public ResponseEntity<APIResponse<Cliente>> save(@Valid @RequestBody Cliente cliente) {
        Cliente savedCliente = clienteService.save(cliente);
        return ResponseUtil.success(savedCliente);
    }

    @PutMapping("/{id}")
    public ResponseEntity<APIResponse<Cliente>> update(@PathVariable Long id, @Valid @RequestBody Cliente cliente) {
        if (clienteService.exists(id)) {
            cliente.setId(id);
            Cliente updatedCliente = clienteService.save(cliente);
            return ResponseUtil.success(updatedCliente);
        } else {
            return ResponseUtil.notFound("Cliente no encontrado");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponse<Object>> delete(@PathVariable Long id) {
        if (clienteService.exists(id)) {
            clienteService.delete(id);
            return ResponseUtil.successDeleted("Cliente con ID {0} eliminado exitosamente", id);
        } else {
            return ResponseUtil.notFound("Cliente no encontrado");
        }
    }

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<APIResponse<Object>> handleValidationException(ConstraintViolationException ex) {
        return ResponseUtil.handleConstraintException(ex);
    }
}

}