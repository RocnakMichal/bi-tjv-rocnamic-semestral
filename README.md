K serveru náleží klient, který se nachází zde: https://gitlab.fit.cvut.cz/rocnamic/bi-tjv-rocnamic-semestral-client

V této práci monitorujeme jací řidiči jezdí do jakých firem. Řidiči pracují na IČO, takže jeden řídič může pracovat pro více firem a mít více aut. Jelikož jsou řidiči externí zaměstnanci v případě jejich odstranění přijdeme i o všechna auta, ktera vlastnili. Pokud se o daté entitě chceme dozvědět více, stači kliknout na její jméno, čí SPZ a získáme o ní další informace, následně můžeme hledat i detaily, na které odkazujeme v detailu původní entity

Operace na více requestů-Nejdříve zadáme request na vztvoření entity, pokud je vše v pořádku příjde další request tentokrát na zobrazení detailu od daném výtvoru.

Dotaz- Může se stál, že odebíráním aut nám vznikne řidič, který nemá žádné auto, takový řidič je pro nás zbytečný a ve větším množství by mohli databázi dělat méně přehlednou, proto je všechny najdeme a jedním kliknutím odstraníme