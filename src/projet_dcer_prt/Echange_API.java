/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet_dcer_prt;
import ModbusClient.ModbusClient;

/**
 *
 * @author valen
 */
public class Echange_API {
    
    public static void main(String[] args)
	{
		ModbusClient modbusClient = new ModbusClient("192.168.209.249",502);//192.168.209.249 automate test salle 205
                                                                                    //192.168.214.128 simulateur
		try
		{
			modbusClient.Connect();
                        int i = 0;
                        modbusClient.WriteSingleCoil(30, true);
                        Thread.sleep(5000);
                        modbusClient.WriteSingleCoil(30, false);
                        //modbusClient.WriteSingleRegister(100, 100);
                        //modbusClient.WriteSingleRegister(101, 100);
                        /*
                        while(i<3900000)
                        {
                            System.out.println("entrée : "+ modbusClient.ReadHoldingRegisters(30, 31)[0]);
                            System.out.println("sortie : "+ modbusClient.ReadHoldingRegisters(31, 32)[0]);
                            //modbusClient.WriteSingleCoil(i, false);
                            //modbusClient.WriteSingleRegister(i, 0);
                            //modbusClient.WriteMultipleRegisters(11, ModbusClient.ConvertFloatToTwoRegisters((float) 123.56));
                            //System.out.println(i + " : "+ modbusClient.ReadCoils(i, i+1)[0]);
                            //System.out.println(i + " : "+ modbusClient.ReadHoldingRegisters(i, i+1)[0]);
                            //System.out.println(i + " : "+ modbusClient.ReadDiscreteInputs(i, i+1)[0]);
                            //System.out.println(i + " : "+ ModbusClient.ConvertRegistersToDouble(modbusClient.ReadHoldingRegisters(i, 2)));
                            //System.out.println(i + " : "+ ModbusClient.ConvertRegistersToDouble(modbusClient.ReadInputRegisters(i, 2)));
                        i++;
                        }*/
                        modbusClient.Disconnect();
			
		}
		catch (Exception e)
		{		
		}	
	}
    
}
