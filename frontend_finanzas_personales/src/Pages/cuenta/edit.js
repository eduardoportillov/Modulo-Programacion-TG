import React, {Component} from 'react';
import {
    SForm,
    SList,
    SNavigation,
    SPage,
    SText,
    STheme,
    SView,
} from 'servisofts-component';
import Model from '../../Model';
import {Container} from '../../Components';

export default class index extends Component {
    constructor(props) {
        super(props);
        this.pk = SNavigation.getParam('pk');
    }
    state = {};
    componentDidMount() {
        Model.cuenta.getByKey({key: this.pk}).then(e => {
            this.setState({cuenta: e});
            Model.categoriacuenta.getalluser().then(arraycc => {
                const cc = arraycc.find(a => a.key == e.keyCategoria);
                this.setState({cc: cc});
            });
        });
    }
    render() {
        return (
            <SPage>
                <Container loading={!this.state.cuenta}>
                    <SForm
                        inputs={{
                            nombre: {
                                type: 'text',
                                label: 'Nombre de la cuenta.',
                                isRequired: true,
                                defaultValue: this.state?.cuenta?.nombre,
                            },
                            keyCategoria: {
                                type: 'text',
                                label: 'Categoria.',
                                isRequired: true,
                                value: this.state?.cc?.nombre,
                                editable: false,
                                onPress: () => {
                                    SNavigation.navigate('/categoriacuenta', {
                                        onSelect: cc => {
                                            this.setState({cc: cc});
                                            SNavigation.goBack();
                                        },
                                    });
                                },
                            },
                        }}
                        onSubmitName={'SUBIR'}
                        loading={this.state.loading}
                        onSubmit={e => {
                            e.keyCategoria = this.state?.cc?.key;

                            this.setState({loading: true});
                            Model.cuenta
                                .edit({
                                    ...this.state.cuenta,
                                    ...e,
                                })
                                .then(e => {
                                    this.setState({loading: false});
                                    console.log(e);
                                    SNavigation.goBack();
                                })
                                .catch(e => {
                                    console.error(e);
                                    this.setState({loading: false});
                                });
                        }}
                    />
                </Container>
            </SPage>
        );
    }
}
