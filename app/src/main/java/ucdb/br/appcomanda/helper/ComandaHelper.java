package ucdb.br.appcomanda.helper;

import ucdb.br.appcomanda.modelDTO.BebidaComandaDTO;
import ucdb.br.appcomanda.modelDTO.ComandaDTO;
import ucdb.br.appcomanda.modelDTO.MesaDTO;
import ucdb.br.appcomanda.modelDTO.PizzaComandaDTO;

/**
 * Created by Lucas on 13/09/2017.
 */

public class ComandaHelper {
    private static ComandaDTO comandaDTO;
    private static MesaDTO mesaDTO;

    public ComandaHelper(){
        comandaDTO = new ComandaDTO();
    }

    public static ComandaDTO getComandaDTO() {
        return comandaDTO;
    }

    public static void setComandaDTO(ComandaDTO comandaDTO) {
        ComandaHelper.comandaDTO = comandaDTO;
    }

    public static Double somaValorComanda(){
      Double result = 0.0;

      if(comandaDTO.getBebidaDTOs() != null){
          for(BebidaComandaDTO bebidaComandaDTO : comandaDTO.getBebidaDTOs()){
              result = result + bebidaComandaDTO.getValorBebida();
          }
      }

      if(comandaDTO.getPizzaDTOs() != null){
          for(PizzaComandaDTO pizzaComandaDTO : comandaDTO.getPizzaDTOs()){
              result = result + pizzaComandaDTO.getValorPizza();
          }
      }

      comandaDTO.setValorComanda(result);
      return result;
    }

    public static MesaDTO getMesaDTO() {
        return mesaDTO;
    }

    public static void setMesaDTO(MesaDTO mesaDTO) {
        ComandaHelper.mesaDTO = mesaDTO;
    }

    public static void limpa(){
        comandaDTO = null;
        mesaDTO = null;
    }
}
