package com.dvidal.bottomsheettest.util

import com.dvidal.bottomsheettest.R
import com.dvidal.bottomsheettest.domain.Action
import com.dvidal.bottomsheettest.domain.Item

/**
 * @author diegovidal on 20/11/18.
 */
object FakeCollection {
    private var actions: ArrayList<Action>? = null
    private var items: ArrayList<Item>? = null

    fun getActions(): ArrayList<Action> {
        if (actions == null) {
            actions = ArrayList()
            actions?.add(Action(R.drawable.ic_copy, "Copy"))
            actions?.add(Action(R.drawable.ic_share, "Share"))
            actions?.add(Action(R.drawable.ic_cut, "Cut"))
            actions?.add(Action(R.drawable.ic_remove, "Remove"))
        }
        return actions as ArrayList<Action>
    }

    fun getItems(): ArrayList<Item> {
        if (items == null) {
            items = ArrayList()
            items?.add(Item(R.drawable.ic_sign_up, "Cadastrar"))
            items?.add(Item(R.drawable.ic_login, "Login"))
            items?.add(Item(R.drawable.ic_contact, "Contato"))
            items?.add(Item(R.drawable.ic_inspector, "Inspetor Device"))
            items?.add(Item(R.drawable.ic_department, "Departamentos"))
            items?.add(Item(R.drawable.ic_deep_link, "Deep link"))
            items?.add(Item(R.drawable.ic_event, "Criar Evento"))
            items?.add(Item(R.drawable.ic_news, "Criar Notícia"))
            items?.add(Item(R.drawable.ic_wall, "Mural"))
        }
        return items as ArrayList<Item>
    }
}