package xcoff;
// This is a generated file! Please edit source .ksy file and use kaitai-struct-compiler to rebuild

import io.kaitai.struct.ByteBufferKaitaiStream;
import io.kaitai.struct.KaitaiStruct;
import io.kaitai.struct.KaitaiStream;
import java.io.IOException;
import java.util.ArrayList;
import java.nio.charset.Charset;
import java.util.Map;
import java.util.HashMap;
import java.util.Arrays;


/**
 * @see <a href="https://www.ibm.com/docs/en/aix/7.2?topic=formats-xcoff-object-file-format">Source</a>
 */
public class Xcoff64 extends KaitaiStruct {
    public static Xcoff64 fromFile(String fileName) throws IOException {
        return new Xcoff64(new ByteBufferKaitaiStream(fileName));
    }

    public Xcoff64(KaitaiStream _io) {
        this(_io, null, null);
    }

    public Xcoff64(KaitaiStream _io, KaitaiStruct _parent) {
        this(_io, _parent, null);
    }

    public Xcoff64(KaitaiStream _io, KaitaiStruct _parent, Xcoff64 _root) {
        super(_io);
        this._parent = _parent;
        this._root = _root == null ? this : _root;
        _read();
    }
    private void _read() {
        this.header = new Header(this._io, this, _root);
        this.auxiliaryHeader = new AuxiliaryHeader(this._io, this, _root);
        this.hpad0 = this._io.readU8be();
        this.hpad1 = this._io.readU2be();
        this._raw_sectionHeaders = new ArrayList<byte[]>(((Number) (header().fNscns())).intValue());
        sectionHeaders = new ArrayList<SectionHeader>(((Number) (header().fNscns())).intValue());
        for (int i = 0; i < header().fNscns(); i++) {
            this._raw_sectionHeaders.add(this._io.readBytes(72));
            KaitaiStream _io__raw_sectionHeaders = new ByteBufferKaitaiStream(_raw_sectionHeaders.get(_raw_sectionHeaders.size() - 1));
            this.sectionHeaders.add(new SectionHeader(_io__raw_sectionHeaders, this, _root));
        }
    }
    public static class ImportEntry extends KaitaiStruct {
        public static ImportEntry fromFile(String fileName) throws IOException {
            return new ImportEntry(new ByteBufferKaitaiStream(fileName));
        }

        public ImportEntry(KaitaiStream _io) {
            this(_io, null, null);
        }

        public ImportEntry(KaitaiStream _io, Xcoff64.ImportTable _parent) {
            this(_io, _parent, null);
        }

        public ImportEntry(KaitaiStream _io, Xcoff64.ImportTable _parent, Xcoff64 _root) {
            super(_io);
            this._parent = _parent;
            this._root = _root;
            _read();
        }
        private void _read() {
            this.lImpidpath = new String(this._io.readBytesTerm(0, false, true, true), Charset.forName("ASCII"));
            this.lImpidbase = new String(this._io.readBytesTerm(0, false, true, true), Charset.forName("ASCII"));
            this.lImpidmem = new String(this._io.readBytesTerm(0, false, true, true), Charset.forName("ASCII"));
        }
        private String lImpidpath;
        private String lImpidbase;
        private String lImpidmem;
        private Xcoff64 _root;
        private Xcoff64.ImportTable _parent;
        public String lImpidpath() { return lImpidpath; }
        public String lImpidbase() { return lImpidbase; }
        public String lImpidmem() { return lImpidmem; }
        public Xcoff64 _root() { return _root; }
        public Xcoff64.ImportTable _parent() { return _parent; }
    }
    public static class ImportTable extends KaitaiStruct {
        public static ImportTable fromFile(String fileName) throws IOException {
            return new ImportTable(new ByteBufferKaitaiStream(fileName));
        }

        public ImportTable(KaitaiStream _io) {
            this(_io, null, null);
        }

        public ImportTable(KaitaiStream _io, Xcoff64.LoaderSection _parent) {
            this(_io, _parent, null);
        }

        public ImportTable(KaitaiStream _io, Xcoff64.LoaderSection _parent, Xcoff64 _root) {
            super(_io);
            this._parent = _parent;
            this._root = _root;
            _read();
        }
        private void _read() {
            importEntries = new ArrayList<ImportEntry>(((Number) (_parent().lNimpid())).intValue());
            for (int i = 0; i < _parent().lNimpid(); i++) {
                this.importEntries.add(new ImportEntry(this._io, this, _root));
            }
        }
        private ArrayList<ImportEntry> importEntries;
        private Xcoff64 _root;
        private Xcoff64.LoaderSection _parent;
        public ArrayList<ImportEntry> importEntries() { return importEntries; }
        public Xcoff64 _root() { return _root; }
        public Xcoff64.LoaderSection _parent() { return _parent; }
    }
    public static class LoaderSection extends KaitaiStruct {
        public static LoaderSection fromFile(String fileName) throws IOException {
            return new LoaderSection(new ByteBufferKaitaiStream(fileName));
        }

        public LoaderSection(KaitaiStream _io) {
            this(_io, null, null);
        }

        public LoaderSection(KaitaiStream _io, Xcoff64.SectionHeader _parent) {
            this(_io, _parent, null);
        }

        public LoaderSection(KaitaiStream _io, Xcoff64.SectionHeader _parent, Xcoff64 _root) {
            super(_io);
            this._parent = _parent;
            this._root = _root;
            _read();
        }
        private void _read() {
            this.lVersion = this._io.readU4be();
            this.lNsyms = this._io.readU4be();
            this.lNreloc = this._io.readU4be();
            this.lIstlen = this._io.readU4be();
            this.lNimpid = this._io.readU4be();
            this.lStlen = this._io.readU4be();
            this.lImpoff = this._io.readU8be();
            this.lStoff = this._io.readU8be();
            this.lSymoff = this._io.readU8be();
            this.lRldoff = this._io.readU4be();
        }
        private SymbolTable lSymbolTable;
        public SymbolTable lSymbolTable() {
            if (this.lSymbolTable != null)
                return this.lSymbolTable;
            KaitaiStream io = _io();
            long _pos = io.pos();
            io.seek(lSymoff());
            this.lSymbolTable = new SymbolTable(io, this, _root);
            io.seek(_pos);
            return this.lSymbolTable;
        }
        private StringTable lStringTable;
        public StringTable lStringTable() {
            if (this.lStringTable != null)
                return this.lStringTable;
            KaitaiStream io = _io();
            long _pos = io.pos();
            io.seek(lStoff());
            this._raw_lStringTable = io.readBytes(lStlen());
            KaitaiStream _io__raw_lStringTable = new ByteBufferKaitaiStream(_raw_lStringTable);
            this.lStringTable = new StringTable(_io__raw_lStringTable, this, _root);
            io.seek(_pos);
            return this.lStringTable;
        }
        private ImportTable lImportTable;
        public ImportTable lImportTable() {
            if (this.lImportTable != null)
                return this.lImportTable;
            KaitaiStream io = _io();
            long _pos = io.pos();
            io.seek(lImpoff());
            this.lImportTable = new ImportTable(io, this, _root);
            io.seek(_pos);
            return this.lImportTable;
        }
        private RelocationTable lRelocTable;
        public RelocationTable lRelocTable() {
            if (this.lRelocTable != null)
                return this.lRelocTable;
            if (lRldoff() != 0) {
                KaitaiStream io = _io();
                long _pos = io.pos();
                io.seek(lRldoff());
                this.lRelocTable = new RelocationTable(io, this, _root);
                io.seek(_pos);
            }
            return this.lRelocTable;
        }
        private long lVersion;
        private long lNsyms;
        private long lNreloc;
        private long lIstlen;
        private long lNimpid;
        private long lStlen;
        private long lImpoff;
        private long lStoff;
        private long lSymoff;
        private long lRldoff;
        private Xcoff64 _root;
        private Xcoff64.SectionHeader _parent;
        private byte[] _raw_lStringTable;
        public long lVersion() { return lVersion; }
        public long lNsyms() { return lNsyms; }
        public long lNreloc() { return lNreloc; }
        public long lIstlen() { return lIstlen; }
        public long lNimpid() { return lNimpid; }
        public long lStlen() { return lStlen; }
        public long lImpoff() { return lImpoff; }
        public long lStoff() { return lStoff; }
        public long lSymoff() { return lSymoff; }
        public long lRldoff() { return lRldoff; }
        public Xcoff64 _root() { return _root; }
        public Xcoff64.SectionHeader _parent() { return _parent; }
        public byte[] _raw_lStringTable() { return _raw_lStringTable; }
    }
    public static class CommonSection extends KaitaiStruct {
        public static CommonSection fromFile(String fileName) throws IOException {
            return new CommonSection(new ByteBufferKaitaiStream(fileName));
        }

        public CommonSection(KaitaiStream _io) {
            this(_io, null, null);
        }

        public CommonSection(KaitaiStream _io, Xcoff64.SectionHeader _parent) {
            this(_io, _parent, null);
        }

        public CommonSection(KaitaiStream _io, Xcoff64.SectionHeader _parent, Xcoff64 _root) {
            super(_io);
            this._parent = _parent;
            this._root = _root;
            _read();
        }
        private void _read() {
            this.body = this._io.readBytes(_parent().sSize());
        }
        private byte[] body;
        private Xcoff64 _root;
        private Xcoff64.SectionHeader _parent;
        public byte[] body() { return body; }
        public Xcoff64 _root() { return _root; }
        public Xcoff64.SectionHeader _parent() { return _parent; }
    }
    public static class SymbolEntry extends KaitaiStruct {
        public static SymbolEntry fromFile(String fileName) throws IOException {
            return new SymbolEntry(new ByteBufferKaitaiStream(fileName));
        }

        public SymbolEntry(KaitaiStream _io) {
            this(_io, null, null);
        }

        public SymbolEntry(KaitaiStream _io, Xcoff64.SymbolTable _parent) {
            this(_io, _parent, null);
        }

        public SymbolEntry(KaitaiStream _io, Xcoff64.SymbolTable _parent, Xcoff64 _root) {
            super(_io);
            this._parent = _parent;
            this._root = _root;
            _read();
        }
        private void _read() {
            this.lValue = this._io.readU8be();
            this._raw_lNameptr = this._io.readBytes(4);
            KaitaiStream _io__raw_lNameptr = new ByteBufferKaitaiStream(_raw_lNameptr);
            this.lNameptr = new SymbolName(_io__raw_lNameptr, this, _root);
            this.lScnum = this._io.readU2be();
            this.lSmtype = new SymbolType(this._io, this, _root);
            this.lSmclas = this._io.readU1();
            this.lIfile = this._io.readU4be();
            this.lParam = this._io.readU4be();
        }
        private long lValue;
        private SymbolName lNameptr;
        private int lScnum;
        private SymbolType lSmtype;
        private int lSmclas;
        private long lIfile;
        private long lParam;
        private Xcoff64 _root;
        private Xcoff64.SymbolTable _parent;
        private byte[] _raw_lNameptr;
        public long lValue() { return lValue; }
        public SymbolName lNameptr() { return lNameptr; }
        public int lScnum() { return lScnum; }
        public SymbolType lSmtype() { return lSmtype; }
        public int lSmclas() { return lSmclas; }
        public long lIfile() { return lIfile; }
        public long lParam() { return lParam; }
        public Xcoff64 _root() { return _root; }
        public Xcoff64.SymbolTable _parent() { return _parent; }
        public byte[] _raw_lNameptr() { return _raw_lNameptr; }
    }
    public static class RelocationEntry extends KaitaiStruct {
        public static RelocationEntry fromFile(String fileName) throws IOException {
            return new RelocationEntry(new ByteBufferKaitaiStream(fileName));
        }

        public RelocationEntry(KaitaiStream _io) {
            this(_io, null, null);
        }

        public RelocationEntry(KaitaiStream _io, Xcoff64.RelocationTable _parent) {
            this(_io, _parent, null);
        }

        public RelocationEntry(KaitaiStream _io, Xcoff64.RelocationTable _parent, Xcoff64 _root) {
            super(_io);
            this._parent = _parent;
            this._root = _root;
            _read();
        }
        private void _read() {
            this.lVaddr = this._io.readU8be();
            this.lValue = this._io.readU2be();
            this.lRsecnm = this._io.readU2be();
            this.lSymndx = this._io.readU4be();
            this.lRtype = this._io.readU4be();
        }
        private long lVaddr;
        private int lValue;
        private int lRsecnm;
        private long lSymndx;
        private long lRtype;
        private Xcoff64 _root;
        private Xcoff64.RelocationTable _parent;
        public long lVaddr() { return lVaddr; }
        public int lValue() { return lValue; }
        public int lRsecnm() { return lRsecnm; }
        public long lSymndx() { return lSymndx; }
        public long lRtype() { return lRtype; }
        public Xcoff64 _root() { return _root; }
        public Xcoff64.RelocationTable _parent() { return _parent; }
    }
    public static class SectionHeader extends KaitaiStruct {
        public static SectionHeader fromFile(String fileName) throws IOException {
            return new SectionHeader(new ByteBufferKaitaiStream(fileName));
        }

        public enum SectionFlags {
            STYP_RESERVED0(0),
            STYP_RESERVED1(1),
            STYP_RESERVED2(2),
            STYP_RESERVED4(4),
            STYP_PAD(8),
            STYP_DWARF(16),
            STYP_TEXT(32),
            STYP_DATA(64),
            STYP_BSS(128),
            STYP_EXCEPT(256),
            STYP_INFO(512),
            STYP_TDATA(1024),
            STYP_TBSS(2048),
            STYP_LOADER(4096),
            STYP_DEBUG(8192),
            STYP_TYPCHK(16384),
            STYP_OVRFLO(32768);

            private final long id;
            SectionFlags(long id) { this.id = id; }
            public long id() { return id; }
            private static final Map<Long, SectionFlags> byId = new HashMap<Long, SectionFlags>(17);
            static {
                for (SectionFlags e : SectionFlags.values())
                    byId.put(e.id(), e);
            }
            public static SectionFlags byId(long id) { return byId.get(id); }
        }

        public SectionHeader(KaitaiStream _io) {
            this(_io, null, null);
        }

        public SectionHeader(KaitaiStream _io, Xcoff64 _parent) {
            this(_io, _parent, null);
        }

        public SectionHeader(KaitaiStream _io, Xcoff64 _parent, Xcoff64 _root) {
            super(_io);
            this._parent = _parent;
            this._root = _root;
            _read();
        }
        private void _read() {
            this.sName = new String(KaitaiStream.bytesTerminate(this._io.readBytes(8), (byte) 0, false), Charset.forName("ASCII"));
            this.sPaddr = this._io.readU8be();
            this.sVaddr = this._io.readU8be();
            this.sSize = this._io.readU8be();
            this.sScnptr = this._io.readU8be();
            this.sRelptr = this._io.readU8be();
            this.sLnnoptr = this._io.readU8be();
            this.sNreloc = this._io.readU4be();
            this.sNlnno = this._io.readU4be();
            this.sFlagsDwraf = this._io.readU2be();
            this.sFlags = this._io.readU2be();
            this.spad = this._io.readU4be();
        }
        private KaitaiStruct subsection;
        public KaitaiStruct subsection() {
            if (this.subsection != null)
                return this.subsection;
            if (sScnptr() != 0) {
                KaitaiStream io = _root._io();
                long _pos = io.pos();
                io.seek(sScnptr());
                switch (sFlags()) {
                case 4096: {
                    this._raw_subsection = io.readBytes(sSize());
                    KaitaiStream _io__raw_subsection = new ByteBufferKaitaiStream(_raw_subsection);
                    this.subsection = new LoaderSection(_io__raw_subsection, this, _root);
                    break;
                }
                default: {
                    this._raw_subsection = io.readBytes(sSize());
                    KaitaiStream _io__raw_subsection = new ByteBufferKaitaiStream(_raw_subsection);
                    this.subsection = new CommonSection(_io__raw_subsection, this, _root);
                    break;
                }
                }
                io.seek(_pos);
            }
            return this.subsection;
        }
        private byte[] body;
        public byte[] body() {
            if (this.body != null)
                return this.body;
            if (sScnptr() != 0) {
                KaitaiStream io = _root._io();
                long _pos = io.pos();
                io.seek(sScnptr());
                this.body = io.readBytes(sSize());
                io.seek(_pos);
            }
            return this.body;
        }
        private String sName;
        private long sPaddr;
        private long sVaddr;
        private long sSize;
        private long sScnptr;
        private long sRelptr;
        private long sLnnoptr;
        private long sNreloc;
        private long sNlnno;
        private int sFlagsDwraf;
        private int sFlags;
        private long spad;
        private Xcoff64 _root;
        private Xcoff64 _parent;
        private byte[] _raw_subsection;
        public String sName() { return sName; }
        public long sPaddr() { return sPaddr; }
        public long sVaddr() { return sVaddr; }
        public long sSize() { return sSize; }
        public long sScnptr() { return sScnptr; }
        public long sRelptr() { return sRelptr; }
        public long sLnnoptr() { return sLnnoptr; }
        public long sNreloc() { return sNreloc; }
        public long sNlnno() { return sNlnno; }
        public int sFlagsDwraf() { return sFlagsDwraf; }
        public int sFlags() { return sFlags; }
        public long spad() { return spad; }
        public Xcoff64 _root() { return _root; }
        public Xcoff64 _parent() { return _parent; }
        public byte[] _raw_subsection() { return _raw_subsection; }
    }
    public static class RelocationTable extends KaitaiStruct {
        public static RelocationTable fromFile(String fileName) throws IOException {
            return new RelocationTable(new ByteBufferKaitaiStream(fileName));
        }

        public RelocationTable(KaitaiStream _io) {
            this(_io, null, null);
        }

        public RelocationTable(KaitaiStream _io, Xcoff64.LoaderSection _parent) {
            this(_io, _parent, null);
        }

        public RelocationTable(KaitaiStream _io, Xcoff64.LoaderSection _parent, Xcoff64 _root) {
            super(_io);
            this._parent = _parent;
            this._root = _root;
            _read();
        }
        private void _read() {
            relocationEntries = new ArrayList<RelocationEntry>(((Number) (_parent().lNreloc())).intValue());
            for (int i = 0; i < _parent().lNreloc(); i++) {
                this.relocationEntries.add(new RelocationEntry(this._io, this, _root));
            }
        }
        private ArrayList<RelocationEntry> relocationEntries;
        private Xcoff64 _root;
        private Xcoff64.LoaderSection _parent;
        public ArrayList<RelocationEntry> relocationEntries() { return relocationEntries; }
        public Xcoff64 _root() { return _root; }
        public Xcoff64.LoaderSection _parent() { return _parent; }
    }
    public static class SymbolType extends KaitaiStruct {
        public static SymbolType fromFile(String fileName) throws IOException {
            return new SymbolType(new ByteBufferKaitaiStream(fileName));
        }

        public SymbolType(KaitaiStream _io) {
            this(_io, null, null);
        }

        public SymbolType(KaitaiStream _io, Xcoff64.SymbolEntry _parent) {
            this(_io, _parent, null);
        }

        public SymbolType(KaitaiStream _io, Xcoff64.SymbolEntry _parent, Xcoff64 _root) {
            super(_io);
            this._parent = _parent;
            this._root = _root;
            _read();
        }
        private void _read() {
            this.symReserved0 = this._io.readBitsIntBe(1) != 0;
            this.symImported = this._io.readBitsIntBe(1) != 0;
            this.symEntrypoint = this._io.readBitsIntBe(1) != 0;
            this.symExported = this._io.readBitsIntBe(1) != 0;
            this.symWeak = this._io.readBitsIntBe(1) != 0;
            this.symType = this._io.readBitsIntBe(3);
        }
        private boolean symReserved0;
        private boolean symImported;
        private boolean symEntrypoint;
        private boolean symExported;
        private boolean symWeak;
        private long symType;
        private Xcoff64 _root;
        private Xcoff64.SymbolEntry _parent;
        public boolean symReserved0() { return symReserved0; }
        public boolean symImported() { return symImported; }
        public boolean symEntrypoint() { return symEntrypoint; }
        public boolean symExported() { return symExported; }
        public boolean symWeak() { return symWeak; }
        public long symType() { return symType; }
        public Xcoff64 _root() { return _root; }
        public Xcoff64.SymbolEntry _parent() { return _parent; }
    }
    public static class AuxiliaryHeader extends KaitaiStruct {
        public static AuxiliaryHeader fromFile(String fileName) throws IOException {
            return new AuxiliaryHeader(new ByteBufferKaitaiStream(fileName));
        }

        public AuxiliaryHeader(KaitaiStream _io) {
            this(_io, null, null);
        }

        public AuxiliaryHeader(KaitaiStream _io, Xcoff64 _parent) {
            this(_io, _parent, null);
        }

        public AuxiliaryHeader(KaitaiStream _io, Xcoff64 _parent, Xcoff64 _root) {
            super(_io);
            this._parent = _parent;
            this._root = _root;
            _read();
        }
        private void _read() {
            this.oMflag = this._io.readU2be();
            this.oVstamp = this._io.readU2be();
            this.oDebugger = this._io.readU4be();
            this.oTextStart = this._io.readU8be();
            this.oDataStart = this._io.readU8be();
            this.oToc = this._io.readU8be();
            this.oSnentry = this._io.readU2be();
            this.oSntext = this._io.readU2be();
            this.oSndata = this._io.readU2be();
            this.oSntoc = this._io.readU2be();
            this.oSnloader = this._io.readU2be();
            this.oSnbss = this._io.readU2be();
            this.oAlgntext = this._io.readU2be();
            this.oAlgndata = this._io.readU2be();
            this.oModtype = this._io.readU2be();
            this.oCpuflag = this._io.readU1();
            this.oCputype = this._io.readU1();
            this.oTextpsize = this._io.readU1();
            this.oDatapsize = this._io.readU1();
            this.oStackpsize = this._io.readU1();
            this.oFlags = this._io.readU1();
            this.oTsize = this._io.readU8be();
            this.oDsize = this._io.readU8be();
            this.oBsize = this._io.readU8be();
            this.oEntry = this._io.readU8be();
            this.oMaxstack = this._io.readU8be();
            this.oMaxdata = this._io.readU8be();
            this.oSntdata = this._io.readU2be();
            this.oSntbss = this._io.readU2be();
            this.oX64flags = this._io.readU2be();
        }
        private int oMflag;
        private int oVstamp;
        private long oDebugger;
        private long oTextStart;
        private long oDataStart;
        private long oToc;
        private int oSnentry;
        private int oSntext;
        private int oSndata;
        private int oSntoc;
        private int oSnloader;
        private int oSnbss;
        private int oAlgntext;
        private int oAlgndata;
        private int oModtype;
        private int oCpuflag;
        private int oCputype;
        private int oTextpsize;
        private int oDatapsize;
        private int oStackpsize;
        private int oFlags;
        private long oTsize;
        private long oDsize;
        private long oBsize;
        private long oEntry;
        private long oMaxstack;
        private long oMaxdata;
        private int oSntdata;
        private int oSntbss;
        private int oX64flags;
        private Xcoff64 _root;
        private Xcoff64 _parent;
        public int oMflag() { return oMflag; }
        public int oVstamp() { return oVstamp; }
        public long oDebugger() { return oDebugger; }
        public long oTextStart() { return oTextStart; }
        public long oDataStart() { return oDataStart; }
        public long oToc() { return oToc; }
        public int oSnentry() { return oSnentry; }
        public int oSntext() { return oSntext; }
        public int oSndata() { return oSndata; }
        public int oSntoc() { return oSntoc; }
        public int oSnloader() { return oSnloader; }
        public int oSnbss() { return oSnbss; }
        public int oAlgntext() { return oAlgntext; }
        public int oAlgndata() { return oAlgndata; }
        public int oModtype() { return oModtype; }
        public int oCpuflag() { return oCpuflag; }
        public int oCputype() { return oCputype; }
        public int oTextpsize() { return oTextpsize; }
        public int oDatapsize() { return oDatapsize; }
        public int oStackpsize() { return oStackpsize; }
        public int oFlags() { return oFlags; }
        public long oTsize() { return oTsize; }
        public long oDsize() { return oDsize; }
        public long oBsize() { return oBsize; }
        public long oEntry() { return oEntry; }
        public long oMaxstack() { return oMaxstack; }
        public long oMaxdata() { return oMaxdata; }
        public int oSntdata() { return oSntdata; }
        public int oSntbss() { return oSntbss; }
        public int oX64flags() { return oX64flags; }
        public Xcoff64 _root() { return _root; }
        public Xcoff64 _parent() { return _parent; }
    }
    public static class StringEntry extends KaitaiStruct {
        public static StringEntry fromFile(String fileName) throws IOException {
            return new StringEntry(new ByteBufferKaitaiStream(fileName));
        }

        public StringEntry(KaitaiStream _io) {
            this(_io, null, null);
        }

        public StringEntry(KaitaiStream _io, Xcoff64.StringTable _parent) {
            this(_io, _parent, null);
        }

        public StringEntry(KaitaiStream _io, Xcoff64.StringTable _parent, Xcoff64 _root) {
            super(_io);
            this._parent = _parent;
            this._root = _root;
            _read();
        }
        private void _read() {
            this.strlen = this._io.readU2be();
            this.str = new String(KaitaiStream.bytesTerminate(this._io.readBytes(strlen()), (byte) 0, false), Charset.forName("ASCII"));
        }
        private int strlen;
        private String str;
        private Xcoff64 _root;
        private Xcoff64.StringTable _parent;
        public int strlen() { return strlen; }
        public String str() { return str; }
        public Xcoff64 _root() { return _root; }
        public Xcoff64.StringTable _parent() { return _parent; }
    }
    public static class Header extends KaitaiStruct {
        public static Header fromFile(String fileName) throws IOException {
            return new Header(new ByteBufferKaitaiStream(fileName));
        }

        public Header(KaitaiStream _io) {
            this(_io, null, null);
        }

        public Header(KaitaiStream _io, Xcoff64 _parent) {
            this(_io, _parent, null);
        }

        public Header(KaitaiStream _io, Xcoff64 _parent, Xcoff64 _root) {
            super(_io);
            this._parent = _parent;
            this._root = _root;
            _read();
        }
        private void _read() {
            this.fMagic = this._io.readBytes(2);
            if (!(Arrays.equals(fMagic(), new byte[] { 1, -9 }))) {
                throw new KaitaiStream.ValidationNotEqualError(new byte[] { 1, -9 }, fMagic(), _io(), "/types/header/seq/0");
            }
            this.fNscns = this._io.readU2be();
            this.fTimdat = this._io.readU4be();
            this.fSymptr = this._io.readU8be();
            this.fOpthdr = this._io.readU2be();
            this.fFlags = this._io.readU2be();
            this.fNsyms = this._io.readU4be();
        }
        private byte[] fMagic;
        private int fNscns;
        private long fTimdat;
        private long fSymptr;
        private int fOpthdr;
        private int fFlags;
        private long fNsyms;
        private Xcoff64 _root;
        private Xcoff64 _parent;
        public byte[] fMagic() { return fMagic; }
        public int fNscns() { return fNscns; }
        public long fTimdat() { return fTimdat; }
        public long fSymptr() { return fSymptr; }
        public int fOpthdr() { return fOpthdr; }
        public int fFlags() { return fFlags; }
        public long fNsyms() { return fNsyms; }
        public Xcoff64 _root() { return _root; }
        public Xcoff64 _parent() { return _parent; }
    }
    public static class StringTable extends KaitaiStruct {
        public static StringTable fromFile(String fileName) throws IOException {
            return new StringTable(new ByteBufferKaitaiStream(fileName));
        }

        public StringTable(KaitaiStream _io) {
            this(_io, null, null);
        }

        public StringTable(KaitaiStream _io, Xcoff64.LoaderSection _parent) {
            this(_io, _parent, null);
        }

        public StringTable(KaitaiStream _io, Xcoff64.LoaderSection _parent, Xcoff64 _root) {
            super(_io);
            this._parent = _parent;
            this._root = _root;
            _read();
        }
        private void _read() {
            this.stringEntries = new ArrayList<StringEntry>();
            {
                int i = 0;
                while (!this._io.isEof()) {
                    this.stringEntries.add(new StringEntry(this._io, this, _root));
                    i++;
                }
            }
        }
        private ArrayList<StringEntry> stringEntries;
        private Xcoff64 _root;
        private Xcoff64.LoaderSection _parent;
        public ArrayList<StringEntry> stringEntries() { return stringEntries; }
        public Xcoff64 _root() { return _root; }
        public Xcoff64.LoaderSection _parent() { return _parent; }
    }
    public static class SymbolName extends KaitaiStruct {
        public static SymbolName fromFile(String fileName) throws IOException {
            return new SymbolName(new ByteBufferKaitaiStream(fileName));
        }

        public SymbolName(KaitaiStream _io) {
            this(_io, null, null);
        }

        public SymbolName(KaitaiStream _io, Xcoff64.SymbolEntry _parent) {
            this(_io, _parent, null);
        }

        public SymbolName(KaitaiStream _io, Xcoff64.SymbolEntry _parent, Xcoff64 _root) {
            super(_io);
            this._parent = _parent;
            this._root = _root;
            _read();
        }
        private void _read() {
            this.lOffset = this._io.readU4be();
        }
        private String lStrname;
        public String lStrname() {
            if (this.lStrname != null)
                return this.lStrname;
            KaitaiStream io = _parent()._parent()._parent().lStringTable()._io();
            long _pos = io.pos();
            io.seek(lOffset());
            this.lStrname = new String(io.readBytesTerm(0, false, true, true), Charset.forName("ASCII"));
            io.seek(_pos);
            return this.lStrname;
        }
        private long lOffset;
        private Xcoff64 _root;
        private Xcoff64.SymbolEntry _parent;
        public long lOffset() { return lOffset; }
        public Xcoff64 _root() { return _root; }
        public Xcoff64.SymbolEntry _parent() { return _parent; }
    }
    public static class SymbolTable extends KaitaiStruct {
        public static SymbolTable fromFile(String fileName) throws IOException {
            return new SymbolTable(new ByteBufferKaitaiStream(fileName));
        }

        public SymbolTable(KaitaiStream _io) {
            this(_io, null, null);
        }

        public SymbolTable(KaitaiStream _io, Xcoff64.LoaderSection _parent) {
            this(_io, _parent, null);
        }

        public SymbolTable(KaitaiStream _io, Xcoff64.LoaderSection _parent, Xcoff64 _root) {
            super(_io);
            this._parent = _parent;
            this._root = _root;
            _read();
        }
        private void _read() {
            symbolEntries = new ArrayList<SymbolEntry>(((Number) (_parent().lNsyms())).intValue());
            for (int i = 0; i < _parent().lNsyms(); i++) {
                this.symbolEntries.add(new SymbolEntry(this._io, this, _root));
            }
        }
        private ArrayList<SymbolEntry> symbolEntries;
        private Xcoff64 _root;
        private Xcoff64.LoaderSection _parent;
        public ArrayList<SymbolEntry> symbolEntries() { return symbolEntries; }
        public Xcoff64 _root() { return _root; }
        public Xcoff64.LoaderSection _parent() { return _parent; }
    }
    private Header header;
    private AuxiliaryHeader auxiliaryHeader;
    private long hpad0;
    private int hpad1;
    private ArrayList<SectionHeader> sectionHeaders;
    private Xcoff64 _root;
    private KaitaiStruct _parent;
    private ArrayList<byte[]> _raw_sectionHeaders;
    public Header header() { return header; }
    public AuxiliaryHeader auxiliaryHeader() { return auxiliaryHeader; }
    public long hpad0() { return hpad0; }
    public int hpad1() { return hpad1; }
    public ArrayList<SectionHeader> sectionHeaders() { return sectionHeaders; }
    public Xcoff64 _root() { return _root; }
    public KaitaiStruct _parent() { return _parent; }
    public ArrayList<byte[]> _raw_sectionHeaders() { return _raw_sectionHeaders; }
}
