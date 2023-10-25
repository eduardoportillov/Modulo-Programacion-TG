import React, {Component, useState} from 'react';
import {
    SForm,
    SHr,
    SList,
    SNavigation,
    SPage,
    SPopup,
    SText,
    STheme,
    SView,
} from 'servisofts-component';
import Model from '../Model';
import {Container, Link} from '../Components';

const PopupElement = ({obj, callback}) => {
    const [state, setState] = useState({});
    return (
        <Container>
            <SHr h={30} />
            <SForm
                inputs={{
                    nombre: {
                        type: 'text',
                        label: 'Nombre',
                        defaultValue: obj.nombre,
                    },
                }}
                onSubmitName={'Guardar'}
                loading={state.loading}
                error={state.error}
                onSubmit={async e => {
                    obj = {
                        ...obj,
                        ...e,
                    };
                    if (callback) {
                        setState({loading: true, error: ''});
                        try {
                            await callback(obj);
                            setState({loading: false, error: ''});
                        } catch (error) {
                            setState({loading: false, error: error});
                        }
                    }
                    SPopup.close(PopupRegistro.name);
                }}
            />
            <SHr h={30} />
        </Container>
    );
};
class PopupRegistro {
    static open({obj, callback}) {
        SPopup.openContainer({
            key: PopupRegistro.name,
            render: () => <PopupElement obj={obj} callback={callback} />,
        });
    }
}

export default class root extends Component {
    constructor(props) {
        super(props);
        this.onSelect = SNavigation.getParam('onSelect');
    }
    state = {};
    componentDidMount() {
        this.setState({loading: true});
        Model.categoriacuenta
            .getalluser()
            .then(e => {
                const data = {};
                e.map(a => (data[a.key] = a));
                console.log(e);
                this.setState({loading: false, data: data});
            })
            .catch(e => {
                console.error(e);
                this.setState({loading: false, error: e});
            });
    }

    renderComponent = obj => {
        return (
            <SView
                card
                padding={8}
                onPress={() => {
                    if (this.onSelect) {
                        this.onSelect(obj);
                        return;
                    }
                }}
            >
                <SHr />
                <SView row>
                    <SText flex bold>
                        {obj.nombre}
                    </SText>

                    <Link
                        color={STheme.color.danger}
                        center
                        width={80}
                        onPress={() => {
                            SPopup.confirm({
                                title: 'Esta seguro de eliminar?',
                                onPress: () => {
                                    Model.categoriacuenta
                                        .delete({key: obj.key})
                                        .then(e => {
                                            delete this.state.data[obj.key];
                                            this.setState({...this.state});
                                        })
                                        .catch(e => {
                                            console.error(e);
                                        });
                                },
                            });
                        }}
                    >
                        Eliminar
                    </Link>
                    <Link
                        color={STheme.color.warning}
                        center
                        width={80}
                        onPress={() => {
                            PopupRegistro.open({
                                obj: obj,
                                callback: async e => {
                                    const resp =
                                        await Model.categoriacuenta.edit(e);
                                    // this.state.data[this.state.data.findIndex(a => a.key == obj.key)] = e
                                    this.state.data[e.key] = e;
                                    this.setState({...this.state});
                                },
                            });
                        }}
                    >
                        Editar
                    </Link>
                </SView>
                <SHr />
            </SView>
        );
    };

    render() {
        return (
            <SPage
                title={'Lista de categorias de cuentas'}
                onRefresh={async resolve => {
                    this.componentDidMount();
                    resolve();
                }}
            >
                <Container loading={this.state.loading}>
                    <SView row col={'xs-12'}>
                        <Link
                            color={STheme.color.success}
                            style={{padding: 4}}
                            onPress={() => {
                                PopupRegistro.open({
                                    obj: {},
                                    callback: async e => {
                                        const resp =
                                            await Model.categoriacuenta.create(
                                                e
                                            );
                                        console.log(resp);
                                        this.state.data[resp.key] = resp;
                                        this.setState({...this.state});
                                    },
                                });
                            }}
                        >{`+ Crear`}</Link>
                    </SView>
                    <SText color={STheme.color.danger}>
                        {this.state?.error}
                    </SText>
                    <SList
                        data={this.state.data}
                        buscador
                        render={this.renderComponent.bind(this)}
                    />
                </Container>
            </SPage>
        );
    }
}
