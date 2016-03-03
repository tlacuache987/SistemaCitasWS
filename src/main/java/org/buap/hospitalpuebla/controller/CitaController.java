package org.buap.hospitalpuebla.controller;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.jws.WebMethod;
import javax.jws.WebService;
import org.buap.hospitalpuebla.model.Cita;
import org.buap.hospitalpuebla.service.ICitaService;
import org.apache.log4j.Logger;

@WebService
public class CitaController implements Serializable {

    private static final Logger LOG = Logger.getLogger(CitaController.class);

    @EJB
    private ICitaService citaService;

    private Cita cita;

    @PostConstruct
    public void init() {
        org.apache.log4j.BasicConfigurator.configure();
    }

    @WebMethod
    public String Registrar(Cita cita) {
        try {
            LOG.info(":::: Registrando cita ");
            citaService.create(cita);
            LOG.info(":::: Cita registrada exitosamente: " + cita);
            return "CITA REGISTRADA EXITOSAMENTE";

        } catch (Exception e) {
            LOG.error(":::: Fallo al registrar");
            return "FALLO EL REGISTRO DE LA CITA";
        }
    }

    @WebMethod
    public String Eliminar(Cita cita) {
        try {
            LOG.info(":::: Eliminando cita");
            citaService.remove(cita);
            LOG.info("cita eliminada exitosamente: " + cita);
            return "CITA ELIMINADA EXITOSAMENTE";
        } catch (Exception e) {
            return "ERROR EN ELIMINAR CITA";
        }
    }

    @WebMethod
    public Cita LeerID(Cita cita) {
        LOG.info(":::: Buscando cita");
        Cita citaTemp = new Cita();
        try {
            citaTemp = citaService.find(cita);
            if (citaTemp != null) {
                return citaTemp;
            }
        } catch (Exception e) {
            return citaTemp;
        }
        return citaTemp;
    }

    public String Modificar(Cita cita) {
        try {
            LOG.info("::: Modificado cita");
            citaService.edit(cita);
            LOG.info("::: Modificada exitosamente");
            return "MODIFICACION EXITOSA";

        } catch (Exception e) {

            return "ERROR EN MODIFICACION";

        }
    }

}
