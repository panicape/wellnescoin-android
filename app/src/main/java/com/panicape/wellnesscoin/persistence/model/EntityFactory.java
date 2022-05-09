package com.panicape.wellnesscoin.persistence.model;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

/**
 * @author panicape
 * @version 1.01
 */
public class EntityFactory {

    public HashMap<String, String> populatePausaTypeToHashmap(PausaTypeDTO pausaType) {
        HashMap<String, String> result = new HashMap<String, String>();

        return result;
    }

    /**
     * Metodo para convertir un UserDto a Hashmap
     *
     * @param user
     * @return
     */
    public HashMap<String, String> populateUserToHashMap(UserDto user) {
        HashMap<String, String> result = new HashMap<String, String>();
        result.put("username", user.getUsername());
        result.put("mail", user.getMail());
        result.put("name", user.getName());
        result.put("phone", user.getPhone());

        return result;
    }

    /**
     * Metodo para convertir un Hashmap a UserDto
     *
      * @param input
     * @return
     */
    public UserDto populateHashMapToUser(HashMap<String, String> input) {
        UserDto user = new UserDto(input.get("username"), input.get("name"),
                input.get("mail"), input.get("phone"));

        return user;
    }

    /**
     *
     * @param wallet
     * @return
     */
    public static HashMap<String, String> populateWalletToHashMap (WalletDto wallet) {
        if (wallet != null) {
            HashMap<String, String> result = new HashMap<String, String>();
            result.put("username", wallet.getUsername());
            result.put("balance", wallet.getBalance().toString());
            SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy-HHmmss");

            result.put("date", sdf.format(new Date()));
            //result.put("password", wallet.getPassword());

            return result;
        }
        return null;
    }

    /**
     *
     * @param input
     * @return
     */
    public static WalletDto populateHashMapToWallet (HashMap<String, String> input) {
        WalletDto result = new WalletDto();
        result.setUsername (input.get ("username"));
        result.setBalance (Float.valueOf (input.get ("balance")));
        result.setLastUpdate (new Date());

        return null;
    }

    /**
     *
     * @param pausaRegistry Pausa activa DTO to be converted to a map
     * @return pausa activa map
     */
    public static HashMap<String, String> populatePausaRegistryToHashmap(PausaRegistryDto pausaRegistry) {
        HashMap<String, String> result = new HashMap<String, String>();

        result.put("pausaType", pausaRegistry.getPausaType());
        result.put("username", pausaRegistry.getUsername());
        result.put("dateCreation", pausaRegistry.getDateCreation());
        result.put("content", pausaRegistry.getContent());

        return result;
    }

    /**
     *
     * @param element map to be converted to a Pausa activa DTO
     * @return Pausa activa DTO
     */
    public static PausaRegistryDto populateHashmapToPausaRegistry(HashMap<String, String> element) {
        PausaRegistryDto pausaRegistryDto = new PausaRegistryDto();

        pausaRegistryDto.setPausaType(element.get("pausaType"));
        pausaRegistryDto.setUsername(element.get("username"));
        pausaRegistryDto.setDateCreation(element.get("dateCreation"));
        pausaRegistryDto.setContent(element.get("content"));

        return pausaRegistryDto;
    }
}
