package dev.SAlmeidaJr.Desafio_tecnico_ITAU.memory;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

@Service
public class MemoriaService {

    private final Map<OffsetDateTime, BigDecimal> armazenamento = new ConcurrentHashMap<>();

    public void save(OffsetDateTime key, BigDecimal value){
        armazenamento.put(key, value);
    }

    public BigDecimal search(OffsetDateTime key){
        return armazenamento.get(key);
    }

    public Set<Entry<OffsetDateTime, BigDecimal>> listAll(){
        return Collections.unmodifiableSet(armazenamento.entrySet());
    }

    public void clear(){
        armazenamento.clear();
    }
}
