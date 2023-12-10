package com.example.pakinta.datasource;

import com.example.pakinta.model.PickWorkerModel;

import java.util.ArrayList;
import java.util.List;

public class PickWorkerDataSource {
    public List<PickWorkerModel> getPickWorkerModels() {
        List<PickWorkerModel> pickWorkerModels = new ArrayList<>();


        pickWorkerModels.add(new PickWorkerModel("Tukang Batu", "Rp.160000", 0, "Menguasai pekerjaan pondasi, pengecoran, dinding, dan yang berkaitan dengan semen, pasir, dan batu"));
        pickWorkerModels.add(new PickWorkerModel("Tukang Kayu", "Rp.160000", 0, "Menguasai kayu kasar termasuk bekisting/ cetakan cor, bowplank, pagar proyek, dan pemasangan kusen"));
        pickWorkerModels.add(new PickWorkerModel("Tukang Besi", "Rp.160000", 0, "Menguasai pekerjaan pemotongan dan perakitan besi tulang untuk struktur beton bertulang"));
        pickWorkerModels.add(new PickWorkerModel("Tukang Atap", "Rp.160000", 0, "Menguasai pekerjaan rangka atap baja ringan serta penutup atap"));
        pickWorkerModels.add(new PickWorkerModel("Tukang Gipsum", "Rp.160000", 0, "Menguasai pekerjaan gipsum untuk plafon dan partisi dinding"));
        pickWorkerModels.add(new PickWorkerModel("Tukang Finishing", "Rp.160000", 0, "Menguasai pekerjaan pengecatan, pelapisan dan perapihan tahap akhir"));
        pickWorkerModels.add(new PickWorkerModel("Tukang Keramik", "Rp.160000", 0, "Menguasai pekerjaan pemasangan dan perbaikan keramik/granit"));
        pickWorkerModels.add(new PickWorkerModel("Tukang Listrik", "Rp.165.000", 0, "Menguasai pekerjaan instalasi dan perbaikan listrik 1 phase dan 3 phase residensial"));
        pickWorkerModels.add(new PickWorkerModel("Tukang Sipil", "Rp.174.500", 0, "Menguasai keahlian tukang batu, kayu bangunan, besi, keramik, dan finishing"));
        pickWorkerModels.add(new PickWorkerModel("Tukang Las", "Rp.185.000", 0, "Menguasai pekerjaan pengelasan dengan metode stick welding"));
        pickWorkerModels.add(new PickWorkerModel("Tukang Waterproofing", "Rp.182.400", 0, "Menguasai permasalahan kebococan air pada dak atap, lantai/diding kamar mandi, dan waterproofing spesialis lainnya"));
        pickWorkerModels.add(new PickWorkerModel("Tukang Struktur", "Rp.168.500", 0, "Memiliki keahlian tukang batu, tukang kayu bangunan, dan tukang besi"));

        return pickWorkerModels;
    }
}

