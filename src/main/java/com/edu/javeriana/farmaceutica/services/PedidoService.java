package com.edu.javeriana.farmaceutica.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.edu.javeriana.farmaceutica.entities.Cliente;
import com.edu.javeriana.farmaceutica.entities.Item;
import com.edu.javeriana.farmaceutica.entities.Medicamento;
import com.edu.javeriana.farmaceutica.entities.Pedido;
import com.edu.javeriana.farmaceutica.models.ItemModelRequest;
import com.edu.javeriana.farmaceutica.models.ItemModelResponse;
import com.edu.javeriana.farmaceutica.models.PedidoModel;
import com.edu.javeriana.farmaceutica.models.PedidoRequestModel;
import com.edu.javeriana.farmaceutica.repositories.ClienteRespository;
import com.edu.javeriana.farmaceutica.repositories.MedicamentoRepository;
import com.edu.javeriana.farmaceutica.repositories.PedidoRepository;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PedidoService {

    private final ClienteRespository clienteRepository;
    private final MedicamentoRepository medicamentoRepository;
    private final PedidoRepository pedidoRepository;
    private final ActivoFisicoService activoFisicoService;

    public PedidoModel crearPedido(Long idCliente, PedidoRequestModel pedidoRequestModel) throws Exception {

        Pedido pedido = new Pedido();

        Cliente cliente = clienteRepository.findById(idCliente)
                .orElseThrow(() -> new Exception("Cliente no encontrado con id: " + idCliente));

        pedido.setCliente(cliente);
        pedido.setDireccionEntrega(pedidoRequestModel.getDireccionEntrega());
        pedido.setFechaPedido(LocalDateTime.now());
        pedido.setZipDestino(pedidoRequestModel.getZipDestino());

        List<Medicamento> medicamentos = new ArrayList<>();
        for (ItemModelRequest item : pedidoRequestModel.getItems()) {
            Optional<Medicamento> medicamento = medicamentoRepository.findById(item.getIdMedicamento());
            if (medicamento.isPresent()) {
                if (activoFisicoService.verificarDisponibilidad(medicamento.get())) {
                    medicamentos.add(medicamento.get());
                }
            }
        }

        for (Medicamento medicamento : medicamentos) {
            for (ItemModelRequest itemRequest : pedidoRequestModel.getItems()) {
                if (medicamento.getIdMedicamento().longValue() == itemRequest.getIdMedicamento().longValue()) {
                    Integer cantidadDisponible = activoFisicoService.verificarCantidadEnExistencia(medicamento,
                            itemRequest.getCantidadSolicitada());
                    Item item = new Item();
                    item.setCantidadSolicitada(itemRequest.getCantidadSolicitada());
                    item.setMedicamento(medicamento);
                    item.setTotalItem(medicamento.getPrecioUnitario() * cantidadDisponible);
                    pedido.agregarItem(item);
                }
            }
        }

        pedido.calcularTotalPedido();

        return mapearPedidoModel(pedidoRepository.save(pedido));
    }

    public PedidoModel obtenerPedido(Long idPedido) throws Exception {

        Pedido pedido = pedidoRepository.findById(idPedido)
                .orElseThrow(() -> new Exception("Pedido no encontrado con id: " + idPedido));

        return mapearPedidoModel(pedido);
    }

    public List<PedidoModel> obtenerListaPedidos() throws Exception {

        List<Pedido> pedidos = pedidoRepository.findAll();
        List<PedidoModel> pedidosModel = new ArrayList<>();

        for (Pedido pedido : pedidos) {
            pedidosModel.add(mapearPedidoModel(pedido));
        }

        return pedidosModel;
    }

    private PedidoModel mapearPedidoModel(Pedido pedido) {

        PedidoModel pedidoModel = new PedidoModel();
        List<ItemModelResponse> itemsResponse = new ArrayList<>();

        pedidoModel.setFechaPedido(pedido.getFechaPedido());
        pedidoModel.setIdPedido(pedido.getIdPedido());
        pedidoModel.setTotalPedido(pedido.getTotalPedido());
        pedidoModel.setDireccionEntrega(pedido.getDireccionEntrega());
        pedidoModel.setZipDestino(pedido.getZipDestino());

        for (Item item : pedido.getItems()) {
            ItemModelResponse itemResponse = new ItemModelResponse();
            itemResponse.setCantidadSolicitada(item.getCantidadSolicitada());
            itemResponse.setIdMedicamento(item.getMedicamento().getIdMedicamento());
            itemResponse.setNombre(item.getMedicamento().getNombre());
            itemResponse.setPrecioUnitario(item.getMedicamento().getPrecioUnitario());
            itemResponse.setTotalItem(item.getTotalItem());
            itemsResponse.add(itemResponse);
        }

        pedidoModel.setItems(itemsResponse);

        return pedidoModel;
    }
}