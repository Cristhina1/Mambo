package com.sistemaFacturacion.Mambo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sistemaFacturacion.Mambo.auth.jwt.JwtService;
import com.sistemaFacturacion.Mambo.entity.Repository.ClienteRepository;
import com.sistemaFacturacion.Mambo.entity.Repository.CompraRepository;
import com.sistemaFacturacion.Mambo.entity.Repository.DestinatarioRepository;
import com.sistemaFacturacion.Mambo.entity.Repository.DetalleCompraRepository;
import com.sistemaFacturacion.Mambo.entity.Repository.EnvioRepository;
import com.sistemaFacturacion.Mambo.entity.Repository.PagoRepository;
import com.sistemaFacturacion.Mambo.entity.model.Compra;
import com.sistemaFacturacion.Mambo.entity.model.Destinatario;
import com.sistemaFacturacion.Mambo.entity.model.DetalleCompra;
import com.sistemaFacturacion.Mambo.entity.model.Envio;
import com.sistemaFacturacion.Mambo.entity.model.TipoComprobante;
import com.sistemaFacturacion.Mambo.entity.model.TipoEnvio;
import com.sistemaFacturacion.Mambo.entity.model.TipoEstado;
import com.sistemaFacturacion.Mambo.entity.model.cliente;
import com.sistemaFacturacion.Mambo.entity.model.pago;
import com.sistemaFacturacion.Mambo.mape.dto.CompraDTO;
import com.sistemaFacturacion.Mambo.mape.dto.CompraRequestDTO;
import com.sistemaFacturacion.Mambo.mape.mapeo.CompraMape;
import com.sistemaFacturacion.Mambo.mape.mapeo.DetalleCompraMape;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CompraService {

    @Autowired
    private CompraRepository compraRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private DetalleCompraRepository detalleCompraRepository;

    @Autowired
    private EnvioRepository envioRepository;
    @Autowired
    private DestinatarioRepository destinatarioRepository;
    @Autowired
    private PagoRepository pagoRepository;

    @Autowired
    private CompraMape compraMape;
    @Autowired
    private DetalleCompraMape detalleCompraMape;

    @Autowired
    private JwtService jwtService;

    @Transactional
    public CompraDTO guardarCarrito(CompraRequestDTO dto, String tokenJWT) {
        // Convertir DTO a entidad
        String usuario = jwtService.getUsernameFromToken(tokenJWT);
        cliente cliente = clienteRepository.findByNumeroDocumento(usuario)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

        // 2️⃣ Guardar ENVÍO
        Envio envio = compraMape.toEnvioEntity(dto.getEnvio());

        if (envio.getTipoEnvio() == TipoEnvio.DELIVERY) {
            envio.setPrecio(20.0);
        } else {
            envio.setPrecio(0.0);
        }

        envio = envioRepository.save(envio);

        // 3️⃣ Guardar DESTINATARIO
        Destinatario destinatario = compraMape.toDestinatarioEntity(dto.getDestinatario());
        destinatario.setCliente(cliente);
        destinatario = destinatarioRepository.save(destinatario);

        // 4️⃣ Guardar PAGO
        pago pago = pagoRepository.save(
                compraMape.toPagoEntity(dto.getPago()));

        Compra compra = new Compra();
        compra.setCliente(cliente);
        compra.setDestinatario(destinatario);
        compra.setEnvio(envio);
        compra.setPago(pago);
        compra.setEstadoPago(TipoEstado.PENDIENTE);
        compra.setTipoComprobante(TipoComprobante.valueOf(dto.getTipoComprobante()));
        compra.setFechaCreacion(LocalDateTime.now());

        double total = dto.getDetalles()
                .stream()
                .mapToDouble(det -> det.getPrecioUnitario() * det.getCantidad())
                .sum();

        compra.setTotal(total + envio.getPrecio());

        Compra compraGuardada = compraRepository.save(compra);

        List<DetalleCompra> detallesGuardados = dto.getDetalles().stream()
                .map(det -> {
                    DetalleCompra detalle = detalleCompraMape.toEntity(det, compraGuardada);
                    return detalleCompraRepository.save(detalle);
                })
                .toList();

        compraGuardada.setDetalles(detallesGuardados);

        // 7️⃣ Devolver DTO final
        return compraMape.toDto(compraGuardada);
    }

    // 📋 Listar todos los carritos
    public List<CompraDTO> listarCarritos() {
        return compraRepository.findAll()
                .stream()
                .map(compraMape::toDto)
                .collect(Collectors.toList());
    }

    // 🔎 Buscar carritos por cliente
    public List<CompraDTO> buscarPorCliente(Long clienteId) {
        return compraRepository.findByClienteId(clienteId).stream()
                .map(compraMape::toDto)
                .collect(Collectors.toList());
    }

}
