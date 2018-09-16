package com.app.demovaadinmodule.vaadin;

import com.app.demovaadinmodule.model.Person;
import com.app.demovaadinmodule.repository.PersonRepository;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.VaadinRequest;
import com.vaadin.shared.data.sort.SortDirection;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.UI;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.vaadin.spring.events.EventBus;
import org.vaadin.spring.events.EventScope;
import org.vaadin.spring.events.annotation.EventBusListenerMethod;
import org.vaadin.viritin.button.ConfirmButton;
import org.vaadin.viritin.button.MButton;
import org.vaadin.viritin.fields.MTextField;
import org.vaadin.viritin.grid.MGrid;
import org.vaadin.viritin.layouts.MHorizontalLayout;
import org.vaadin.viritin.layouts.MVerticalLayout;

import java.util.ArrayList;
import java.util.List;

@Title("Person with Vaadin")
@Theme("valo")
@SpringUI
public class MainUI extends UI {

    private static final long serialVersionUID = 1L;

    PersonForm personForm;
    PersonRepository personRepository;
    EventBus.UIEventBus eventBus;

    /** declare component **/
    private MGrid<Person> gridPerson = new MGrid<>(Person.class)
            .withProperties("id", "firstname", "lastname", "email", "address")
            .withColumnHeaders("ID", "Firstname", "Lastname", "Email", "Address")
            .withFullWidth();

    private MTextField filterByFirstname = new MTextField()
            .withPlaceholder("Search");
    private Button btnSave = new MButton(VaadinIcons.PLUS, this::add);
    private Button btnUpdate = new MButton(VaadinIcons.PENCIL, this::update);
    private Button btnDelete = new ConfirmButton(VaadinIcons.TRASH,
            "sure delete this data ?", this::delete);

    public MainUI(PersonRepository pr, PersonForm pf, EventBus.UIEventBus ev){
        this.personRepository = pr;
        this.personForm = pf;
        this.eventBus = ev;
    }

    @Override
    protected void init(VaadinRequest request){
       setContent(
               new MVerticalLayout(
                       new MHorizontalLayout(
                               filterByFirstname,
                               btnSave,
                               btnUpdate,
                               btnDelete),
                       gridPerson).expand(gridPerson));
       listEntities();

       gridPerson.asSingleSelect()
               .addValueChangeListener(e -> addjustbuttonstate());
       filterByFirstname.addValueChangeListener(e -> {
           listEntities(e.getValue());
       });
       eventBus.subscribe(this);
    }

    /** searching **/
    private void listEntities(String filterFirstName){
        String likeFilter = "%"+filterFirstName+"%";

        gridPerson.setDataProvider(
                (sortOrder, offset, limit) -> {
                    final int pageSize = limit;
                    final int startPage = (int) Math.floor((double) offset / pageSize);
                    final int endPage = (int) Math.floor((double) (offset + pageSize - 1) / pageSize);
                    final Sort.Direction sortDirection = sortOrder.isEmpty() || sortOrder.get(0).getDirection() == SortDirection.ASCENDING ? Sort.Direction.ASC : Sort.Direction.DESC;
                    final String sortProperty = sortOrder.isEmpty() ? "id" : sortOrder.get(0).getSorted();
                    if (startPage!=endPage){

                        List<Person> page0 = personRepository
                                .findByFirstnameLikeIgnoreCase(likeFilter, PageRequest
                                        .of(startPage, pageSize, sortDirection, sortProperty));
                        page0 = page0.subList(offset % pageSize, page0.size());
                        List<Person> page1 = personRepository
                                .findByFirstnameLikeIgnoreCase(likeFilter, PageRequest
                                .of(endPage, pageSize, sortDirection, sortProperty));
                        page1 = page1.subList(0, limit - page0.size());
                        List<Person> result = new ArrayList<>(page0);
                        result.addAll(page1);
                        return result.stream();
                    }else {
                        return personRepository.findByFirstnameLikeIgnoreCase(likeFilter,
                                PageRequest.of(startPage, pageSize, sortDirection, sortProperty)).stream();
                    }
                },
                () -> (int) personRepository.countByFirstnameLikeIgnoreCase(likeFilter));
        /** addjust button state **/
        addjustbuttonstate();
    }

    /** addjust button state **/
    protected void addjustbuttonstate(){
        boolean hasSelection =! gridPerson.getSelectedItems().isEmpty();
        btnDelete.setEnabled(hasSelection);
        /** akan aktive ketika row dipilih **/
        btnUpdate.setEnabled(hasSelection);
        /** akan aktive ketika row dipilih **/
    }

    private void listEntities(){
        listEntities(filterByFirstname.getValue());
    }

    /** add action **/
    public void add(ClickEvent clickEvent){
        update(new Person());
    }

    /** delete action **/
    public void delete(){
        personRepository.delete(gridPerson.asSingleSelect().getValue());
        gridPerson.deselectAll();
        listEntities();
    }

    /** update action **/
    public void update(ClickEvent clickEvent){
        update(gridPerson.asSingleSelect().getValue());
    }

    /** handle for save or update **/
    protected void update(final Person personEntry){
        personForm.setEntity(personEntry);
        personForm.openInModalPopup();
    }

    /** event bus listener **/
    @EventBusListenerMethod(scope = EventScope.UI)
    public void onPersonModified(PersonModifiedEvent event){
        listEntities();
        personForm.closePopup();
    }
}
