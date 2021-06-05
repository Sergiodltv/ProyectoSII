package practicaSII.ejb.clasesejb;

import practicaSII.Grupo;
import practicaSII.ejb.excetption.GrupoNoEncontradoException;
import practicaSII.ejb.excetption.GrupoEncontradoException;

public interface GestionGrupos {

        public void anadirGrupo(Grupo gr) throws GrupoEncontradoException;

        public void eliminarGrupo(Grupo gr) throws GrupoNoEncontradoException;

        public void modificarGrupo(Grupo gr) throws GrupoNoEncontradoException;

}
